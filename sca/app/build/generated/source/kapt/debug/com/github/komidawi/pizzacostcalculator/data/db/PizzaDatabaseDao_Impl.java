package com.github.komidawi.pizzacostcalculator.data.db;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PizzaDatabaseDao_Impl implements PizzaDatabaseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PizzaEntity> __insertionAdapterOfPizzaEntity;

  private final EntityDeletionOrUpdateAdapter<PizzaEntity> __updateAdapterOfPizzaEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByUuid;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PizzaDatabaseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPizzaEntity = new EntityInsertionAdapter<PizzaEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `pizza` (`pizzeria`,`name`,`size`,`price`,`ratio`,`delivery_cost`,`uuid`,`id`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PizzaEntity value) {
        if (value.getPizzeria() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPizzeria());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSize() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSize());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrice());
        }
        if (value.getRatio() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRatio());
        }
        if (value.getDeliveryCost() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDeliveryCost());
        }
        if (value.getUuid() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUuid());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getId());
        }
      }
    };
    this.__updateAdapterOfPizzaEntity = new EntityDeletionOrUpdateAdapter<PizzaEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `pizza` SET `pizzeria` = ?,`name` = ?,`size` = ?,`price` = ?,`ratio` = ?,`delivery_cost` = ?,`uuid` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PizzaEntity value) {
        if (value.getPizzeria() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPizzeria());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSize() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSize());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrice());
        }
        if (value.getRatio() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRatio());
        }
        if (value.getDeliveryCost() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDeliveryCost());
        }
        if (value.getUuid() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUuid());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getId());
        }
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM pizza WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByUuid = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM pizza WHERE uuid = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM pizza";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final PizzaEntity pizzaEntity, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPizzaEntity.insert(pizzaEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object insertAll(final List<PizzaEntity> pizzaEntities,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPizzaEntity.insert(pizzaEntities);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object update(final PizzaEntity pizzaEntity, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPizzaEntity.handle(pizzaEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, p1);
  }

  @Override
  public Object deleteByUuid(final String uuid, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByUuid.acquire();
        int _argIndex = 1;
        if (uuid == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, uuid);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteByUuid.release(_stmt);
        }
      }
    }, p1);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getById(final long id, final Continuation<? super PizzaEntity> p1) {
    final String _sql = "SELECT * FROM pizza WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.execute(__db, false, new Callable<PizzaEntity>() {
      @Override
      public PizzaEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPizzeria = CursorUtil.getColumnIndexOrThrow(_cursor, "pizzeria");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRatio = CursorUtil.getColumnIndexOrThrow(_cursor, "ratio");
          final int _cursorIndexOfDeliveryCost = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_cost");
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final PizzaEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpPizzeria;
            _tmpPizzeria = _cursor.getString(_cursorIndexOfPizzeria);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSize;
            _tmpSize = _cursor.getString(_cursorIndexOfSize);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpRatio;
            _tmpRatio = _cursor.getString(_cursorIndexOfRatio);
            final String _tmpDeliveryCost;
            _tmpDeliveryCost = _cursor.getString(_cursorIndexOfDeliveryCost);
            final String _tmpUuid;
            _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _result = new PizzaEntity(_tmpPizzeria,_tmpName,_tmpSize,_tmpPrice,_tmpRatio,_tmpDeliveryCost,_tmpUuid,_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  @Override
  public Object getByUuid(final String uuid, final Continuation<? super PizzaEntity> p1) {
    final String _sql = "SELECT * FROM pizza WHERE uuid = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uuid == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uuid);
    }
    return CoroutinesRoom.execute(__db, false, new Callable<PizzaEntity>() {
      @Override
      public PizzaEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPizzeria = CursorUtil.getColumnIndexOrThrow(_cursor, "pizzeria");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRatio = CursorUtil.getColumnIndexOrThrow(_cursor, "ratio");
          final int _cursorIndexOfDeliveryCost = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_cost");
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final PizzaEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpPizzeria;
            _tmpPizzeria = _cursor.getString(_cursorIndexOfPizzeria);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSize;
            _tmpSize = _cursor.getString(_cursorIndexOfSize);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpRatio;
            _tmpRatio = _cursor.getString(_cursorIndexOfRatio);
            final String _tmpDeliveryCost;
            _tmpDeliveryCost = _cursor.getString(_cursorIndexOfDeliveryCost);
            final String _tmpUuid;
            _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _result = new PizzaEntity(_tmpPizzeria,_tmpName,_tmpSize,_tmpPrice,_tmpRatio,_tmpDeliveryCost,_tmpUuid,_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  @Override
  public LiveData<List<PizzaEntity>> getAll() {
    final String _sql = "SELECT * FROM pizza ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"pizza"}, false, new Callable<List<PizzaEntity>>() {
      @Override
      public List<PizzaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPizzeria = CursorUtil.getColumnIndexOrThrow(_cursor, "pizzeria");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRatio = CursorUtil.getColumnIndexOrThrow(_cursor, "ratio");
          final int _cursorIndexOfDeliveryCost = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_cost");
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<PizzaEntity> _result = new ArrayList<PizzaEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PizzaEntity _item;
            final String _tmpPizzeria;
            _tmpPizzeria = _cursor.getString(_cursorIndexOfPizzeria);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpSize;
            _tmpSize = _cursor.getString(_cursorIndexOfSize);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpRatio;
            _tmpRatio = _cursor.getString(_cursorIndexOfRatio);
            final String _tmpDeliveryCost;
            _tmpDeliveryCost = _cursor.getString(_cursorIndexOfDeliveryCost);
            final String _tmpUuid;
            _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item = new PizzaEntity(_tmpPizzeria,_tmpName,_tmpSize,_tmpPrice,_tmpRatio,_tmpDeliveryCost,_tmpUuid,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
