# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Android app (Kotlin) that helps choose the best pizza by price/area ratio. Small codebase,
deliberately over-engineered for practice: TDD, layered architecture, high test coverage,
CI (Bitrise), SonarQube + CodeCov metrics.

## Commands

All commands run from the repo root via the Gradle wrapper.

```
./gradlew assembleDebug                  # build debug APK
./gradlew testDebugUnitTest              # run local JVM unit tests (app/src/test + sharedTest)
./gradlew connectedDebugAndroidTest       # run instrumented tests on a device/emulator (app/src/androidTest + sharedTest)
./gradlew test --tests "*.CostCalculatorTest"                 # run a single unit test class
./gradlew test --tests "*.CostCalculatorTest.someTestMethod"  # run a single unit test method
./gradlew testDebugUnitTestCoverage      # unit tests + Jacoco coverage report (defined in jacoco.gradle)
./gradlew sonarqube                      # run SonarQube analysis (needs env.gradle, see below)
```

On Windows use `gradlew.bat` instead of `./gradlew`.

There is no lint/ktlint task configured beyond Android Lint (`./gradlew lint`).

## Local config required before building

- `app/src/main/java/com/github/komidawi/pizzacostcalculator/config/Env.kt` — gitignored, copy
  from the committed `EnvTemplate.kt` in the same package and fill in `BASE_URL` for the
  Retrofit-based `RestApiService` (network layer exists but isn't wired into the app flow yet —
  all persistence goes through Room, not the network API).
- `env.gradle` — gitignored, holds `SONAR_HOST_URL`/`SONAR_TOKEN` used by `sonar.gradle`. Only
  needed for the `sonarqube` task.

## Architecture

Layered, ViewModel-driven, no DI framework — dependencies are wired manually via a
`ServiceLocator` (`app/src/main/java/.../ServiceLocator.kt`, singleton object) and a
`ViewModelFactory`.

Dependency flow (top to bottom):

```
Fragment (screen/list, screen/add)
  -> ViewModel (via ViewModelFactory, constructed with the app's PizzaRepository)
     -> PizzaRepository (interface) / PizzaRepositoryImpl
        -> DataSource (interface) / LocalDataSource
           -> PizzaDatabaseDao (Room) -> PizzaEntity
```

- `PizzaApplication.pizzaRepository` lazily asks `ServiceLocator.providePizzaRepository(context)`
  for the singleton repository; Room database + DAO + repository are all created and cached
  there. Fragments read `(requireContext().applicationContext as PizzaApplication).pizzaRepository`
  and hand it to `ViewModelFactory` — there's no `ViewModelProvider.Factory` DI, each Fragment
  constructs its own ViewModel directly in `onCreateView`.
- `domain.Pizza` is the domain model; `data.db.PizzaEntity` is the Room row (numeric fields stored
  as `String` — `BigDecimal.toString()`/`BigDecimal(String)` conversions). Mapping happens via
  `Pizza.asDatabaseModel()` (in `domain/Pizza.kt`) and `PizzaEntity.asDomainModel()` (in
  `data/db/PizzaEntity.kt`), not inside the repository or data source.
- `calc.CostCalculator` / `CostCalculatorImpl` computes price-per-square-meter ratio from a pizza's
  circular size (diameter) and price; this is the core business rule the whole app exists for.
  `domain.PizzaFactory` is the only place that constructs a `Pizza` with a computed `ratio` from
  raw string/numeric input (used from `AddPizzaFragmentViewModel` and test factories).
- Room schema is versioned (`PizzaDatabase`, currently v5) with explicit migrations in
  `data/db/Migrations.kt` (e.g. table rename `pizza_table` -> `pizza`, added columns). Schemas are
  exported to `app/schemas` (`room.schemaLocation`) — bump the `@Database(version = ...)` and add a
  `Migration` object whenever `PizzaEntity` changes shape.
- UI uses Jetpack Navigation (single graph: `app/src/main/res/navigation/navigation.xml`, two
  destinations: `PizzaListFragment` <-> `AddPizzaFragment`) plus data binding/view binding
  (`binding.viewModel = viewModel`, both `viewBinding` and `dataBinding` are enabled in
  `app/build.gradle`). Navigation between fragments is triggered by ViewModel LiveData flags
  (e.g. `navigateToPizzaListFragment`) observed in the Fragment, not by direct navigation calls
  from the ViewModel.
- `network/` (Retrofit `RestApiService`, `PizzaDto`) exists as a data source but is not currently
  used by any repository/ViewModel — treat it as scaffolding, not a live code path, unless asked
  to wire it in.

## Testing structure

Three source sets share the `com.github.komidawi.pizzacostcalculator` package layout:

- `app/src/test` — local JVM unit tests (Robolectric + Mockito + Truth), e.g.
  `CostCalculatorTest`, `AddPizzaFragmentViewModelTest`, `PizzaListFragmentViewModelTest`.
  `helper/MainCoroutineRule` swaps `Dispatchers.Main` for a `TestCoroutineDispatcher` — use it
  (`@get:Rule`) in any ViewModel test that launches coroutines via `viewModelScope`/`uiScope`.
- `app/src/androidTest` — instrumented tests (Espresso, Room in-memory DB, Fragment scenarios),
  e.g. `PizzaDatabaseDaoTest`, `PizzaListFragmentTest`, `AddPizzaFragmentTest`.
- `app/src/sharedTest` — test doubles/factories used by both of the above (registered as an extra
  `java.srcDir` for both `test` and `androidTest` in `app/build.gradle`):
  - `FakeDatabaseDao` — in-memory fake implementing `PizzaDatabaseDao`, no Room involved.
  - `TestRepositoryFactory` — builds a real `PizzaRepositoryImpl` wired to `FakeDatabaseDao`, for
    tests that want a working repository without touching Room.
  - `TestPizzaFactory` / `TestPizzaData` — canned `Pizza` fixtures for tests.
  - `TestViewModelFactory` — test-side equivalent of `ViewModelFactory`.

When adding a new persistence-backed feature, prefer extending the `DataSource`/`PizzaRepository`
interfaces plus their fake counterparts in `sharedTest`, matching the existing pattern, rather than
mocking Room directly in unit tests.
