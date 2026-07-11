package com.github.komidawi.pizzacostcalculator.databinding;
import com.github.komidawi.pizzacostcalculator.R;
import com.github.komidawi.pizzacostcalculator.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentAddPizzaBindingImpl extends FragmentAddPizzaBinding implements com.github.komidawi.pizzacostcalculator.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener pizzaDeliveryCostInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.deliveryCost.getValue()
            //         is viewModel.deliveryCost.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(pizzaDeliveryCostInput);
            // localize variables for thread safety
            // viewModel.deliveryCost
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDeliveryCost = null;
            // viewModel.deliveryCost.getValue()
            java.lang.String viewModelDeliveryCostGetValue = null;
            // viewModel
            com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.deliveryCost != null
            boolean viewModelDeliveryCostJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelDeliveryCost = viewModel.getDeliveryCost();

                viewModelDeliveryCostJavaLangObjectNull = (viewModelDeliveryCost) != (null);
                if (viewModelDeliveryCostJavaLangObjectNull) {




                    viewModelDeliveryCost.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener pizzaNameInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.name.getValue()
            //         is viewModel.name.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(pizzaNameInput);
            // localize variables for thread safety
            // viewModel.name != null
            boolean viewModelNameJavaLangObjectNull = false;
            // viewModel.name
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelName = null;
            // viewModel
            com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.name.getValue()
            java.lang.String viewModelNameGetValue = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelName = viewModel.getName();

                viewModelNameJavaLangObjectNull = (viewModelName) != (null);
                if (viewModelNameJavaLangObjectNull) {




                    viewModelName.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener pizzaPizzeriaInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.pizzeria.getValue()
            //         is viewModel.pizzeria.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(pizzaPizzeriaInput);
            // localize variables for thread safety
            // viewModel.pizzeria
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelPizzeria = null;
            // viewModel.pizzeria != null
            boolean viewModelPizzeriaJavaLangObjectNull = false;
            // viewModel
            com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;
            // viewModel.pizzeria.getValue()
            java.lang.String viewModelPizzeriaGetValue = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelPizzeria = viewModel.getPizzeria();

                viewModelPizzeriaJavaLangObjectNull = (viewModelPizzeria) != (null);
                if (viewModelPizzeriaJavaLangObjectNull) {




                    viewModelPizzeria.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener pizzaPriceInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.price.getValue()
            //         is viewModel.price.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(pizzaPriceInput);
            // localize variables for thread safety
            // viewModel.price.getValue()
            java.lang.String viewModelPriceGetValue = null;
            // viewModel.price != null
            boolean viewModelPriceJavaLangObjectNull = false;
            // viewModel
            com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.price
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelPrice = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelPrice = viewModel.getPrice();

                viewModelPriceJavaLangObjectNull = (viewModelPrice) != (null);
                if (viewModelPriceJavaLangObjectNull) {




                    viewModelPrice.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener pizzaSizeInputandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.size.getValue()
            //         is viewModel.size.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(pizzaSizeInput);
            // localize variables for thread safety
            // viewModel.size
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelSize = null;
            // viewModel.size != null
            boolean viewModelSizeJavaLangObjectNull = false;
            // viewModel
            com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;
            // viewModel.size.getValue()
            java.lang.String viewModelSizeGetValue = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelSize = viewModel.getSize();

                viewModelSizeJavaLangObjectNull = (viewModelSize) != (null);
                if (viewModelSizeJavaLangObjectNull) {




                    viewModelSize.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentAddPizzaBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private FragmentAddPizzaBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6
            , (android.widget.TextView) bindings[6]
            , (android.widget.Button) bindings[7]
            , (android.widget.EditText) bindings[5]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[3]
            );
        this.listPizzaRatioDisplay.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pizzaAddButton.setTag(null);
        this.pizzaDeliveryCostInput.setTag(null);
        this.pizzaNameInput.setTag(null);
        this.pizzaPizzeriaInput.setTag(null);
        this.pizzaPriceInput.setTag(null);
        this.pizzaSizeInput.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.github.komidawi.pizzacostcalculator.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelPizzeria((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelPrice((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelSize((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeViewModelDeliveryCost((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewModelRatio((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeViewModelName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelPizzeria(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelPizzeria, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelPrice(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelPrice, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelSize(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelSize, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelDeliveryCost(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelDeliveryCost, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelRatio(androidx.lifecycle.LiveData<java.lang.String> ViewModelRatio, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelName(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String viewModelPizzeriaGetValue = null;
        java.lang.String viewModelSizeGetValue = null;
        java.lang.String viewModelNameGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelPizzeria = null;
        java.lang.String listPizzaRatioDisplayAndroidStringPizzaRatioDisplayTextViewModelRatio = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelPrice = null;
        java.lang.String viewModelRatioGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelSize = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelDeliveryCost = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelRatio = null;
        java.lang.String viewModelPriceGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelName = null;
        java.lang.String viewModelDeliveryCostGetValue = null;
        com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0xffL) != 0) {


            if ((dirtyFlags & 0xc1L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.pizzeria
                        viewModelPizzeria = viewModel.getPizzeria();
                    }
                    updateLiveDataRegistration(0, viewModelPizzeria);


                    if (viewModelPizzeria != null) {
                        // read viewModel.pizzeria.getValue()
                        viewModelPizzeriaGetValue = viewModelPizzeria.getValue();
                    }
            }
            if ((dirtyFlags & 0xc2L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.price
                        viewModelPrice = viewModel.getPrice();
                    }
                    updateLiveDataRegistration(1, viewModelPrice);


                    if (viewModelPrice != null) {
                        // read viewModel.price.getValue()
                        viewModelPriceGetValue = viewModelPrice.getValue();
                    }
            }
            if ((dirtyFlags & 0xc4L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.size
                        viewModelSize = viewModel.getSize();
                    }
                    updateLiveDataRegistration(2, viewModelSize);


                    if (viewModelSize != null) {
                        // read viewModel.size.getValue()
                        viewModelSizeGetValue = viewModelSize.getValue();
                    }
            }
            if ((dirtyFlags & 0xc8L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.deliveryCost
                        viewModelDeliveryCost = viewModel.getDeliveryCost();
                    }
                    updateLiveDataRegistration(3, viewModelDeliveryCost);


                    if (viewModelDeliveryCost != null) {
                        // read viewModel.deliveryCost.getValue()
                        viewModelDeliveryCostGetValue = viewModelDeliveryCost.getValue();
                    }
            }
            if ((dirtyFlags & 0xd0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.ratio
                        viewModelRatio = viewModel.getRatio();
                    }
                    updateLiveDataRegistration(4, viewModelRatio);


                    if (viewModelRatio != null) {
                        // read viewModel.ratio.getValue()
                        viewModelRatioGetValue = viewModelRatio.getValue();
                    }


                    // read @android:string/pizza_ratio_display_text
                    listPizzaRatioDisplayAndroidStringPizzaRatioDisplayTextViewModelRatio = listPizzaRatioDisplay.getResources().getString(R.string.pizza_ratio_display_text, viewModelRatioGetValue);
            }
            if ((dirtyFlags & 0xe0L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.name
                        viewModelName = viewModel.getName();
                    }
                    updateLiveDataRegistration(5, viewModelName);


                    if (viewModelName != null) {
                        // read viewModel.name.getValue()
                        viewModelNameGetValue = viewModelName.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xd0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.listPizzaRatioDisplay, listPizzaRatioDisplayAndroidStringPizzaRatioDisplayTextViewModelRatio);
        }
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            this.pizzaAddButton.setOnClickListener(mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.pizzaDeliveryCostInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, pizzaDeliveryCostInputandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.pizzaNameInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, pizzaNameInputandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.pizzaPizzeriaInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, pizzaPizzeriaInputandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.pizzaPriceInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, pizzaPriceInputandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.pizzaSizeInput, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, pizzaSizeInputandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xc8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.pizzaDeliveryCostInput, viewModelDeliveryCostGetValue);
        }
        if ((dirtyFlags & 0xe0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.pizzaNameInput, viewModelNameGetValue);
        }
        if ((dirtyFlags & 0xc1L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.pizzaPizzeriaInput, viewModelPizzeriaGetValue);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.pizzaPriceInput, viewModelPriceGetValue);
        }
        if ((dirtyFlags & 0xc4L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.pizzaSizeInput, viewModelSizeGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;
        // viewModel.handleAddPizza()
        com.github.komidawi.pizzacostcalculator.domain.Pizza viewModelHandleAddPizza = null;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModelHandleAddPizza = viewModel.handleAddPizza();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.pizzeria
        flag 1 (0x2L): viewModel.price
        flag 2 (0x3L): viewModel.size
        flag 3 (0x4L): viewModel.deliveryCost
        flag 4 (0x5L): viewModel.ratio
        flag 5 (0x6L): viewModel.name
        flag 6 (0x7L): viewModel
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}