package com.github.komidawi.pizzacostcalculator.databinding;
import com.github.komidawi.pizzacostcalculator.R;
import com.github.komidawi.pizzacostcalculator.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListItemPizzaElementBindingImpl extends ListItemPizzaElementBinding implements com.github.komidawi.pizzacostcalculator.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.pizza_pizzeria_label, 9);
        sViewsWithIds.put(R.id.pizza_size_label, 10);
        sViewsWithIds.put(R.id.pizza_price_label, 11);
        sViewsWithIds.put(R.id.pizza_ratio_label, 12);
        sViewsWithIds.put(R.id.pizza_name_label, 13);
        sViewsWithIds.put(R.id.pizza_delivery_cost_label, 14);
        sViewsWithIds.put(R.id.pizza_total_cost_label, 15);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListItemPizzaElementBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private ListItemPizzaElementBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (android.widget.ImageButton) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[8]
            );
        this.listItemPizzaElementRoot.setTag(null);
        this.listPizzaRatioDisplay.setTag(null);
        this.pizzaDeliveryCostDisplay.setTag(null);
        this.pizzaNameDisplay.setTag(null);
        this.pizzaPizzeriaDisplay.setTag(null);
        this.pizzaPriceDisplay.setTag(null);
        this.pizzaRemoveButton.setTag(null);
        this.pizzaSizeDisplay.setTag(null);
        this.textView4.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new com.github.komidawi.pizzacostcalculator.generated.callback.OnClickListener(this, 2);
        mCallback2 = new com.github.komidawi.pizzacostcalculator.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.clickListener == variableId) {
            setClickListener((com.github.komidawi.pizzacostcalculator.screen.list.PizzaItemListener) variable);
        }
        else if (BR.pizza == variableId) {
            setPizza((com.github.komidawi.pizzacostcalculator.domain.Pizza) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.github.komidawi.pizzacostcalculator.screen.list.PizzaListFragmentViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setClickListener(@Nullable com.github.komidawi.pizzacostcalculator.screen.list.PizzaItemListener ClickListener) {
        this.mClickListener = ClickListener;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.clickListener);
        super.requestRebind();
    }
    public void setPizza(@Nullable com.github.komidawi.pizzacostcalculator.domain.Pizza Pizza) {
        this.mPizza = Pizza;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.pizza);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.github.komidawi.pizzacostcalculator.screen.list.PizzaListFragmentViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        com.github.komidawi.pizzacostcalculator.screen.list.PizzaItemListener clickListener = mClickListener;
        com.github.komidawi.pizzacostcalculator.domain.Pizza pizza = mPizza;
        com.github.komidawi.pizzacostcalculator.screen.list.PizzaListFragmentViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0xaL) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.listItemPizzaElementRoot.setOnClickListener(mCallback2);
            this.pizzaRemoveButton.setOnClickListener(mCallback3);
        }
        if ((dirtyFlags & 0xaL) != 0) {
            // api target 1

            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setListPizzaRatioDisplay(this.listPizzaRatioDisplay, pizza);
            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setPizzaDeliveryCostDisplay(this.pizzaDeliveryCostDisplay, pizza);
            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setPizzaNameDisplay(this.pizzaNameDisplay, pizza);
            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setPizzaPizzeriaDisplay(this.pizzaPizzeriaDisplay, pizza);
            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setPizzaPriceDisplay(this.pizzaPriceDisplay, pizza);
            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setPizzaSizeDisplay(this.pizzaSizeDisplay, pizza);
            com.github.komidawi.pizzacostcalculator.screen.list.BindingAdaptersKt.setPizzaTotalCostDisplay(this.textView4, pizza);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // pizza
                com.github.komidawi.pizzacostcalculator.domain.Pizza pizza = mPizza;
                // viewModel
                com.github.komidawi.pizzacostcalculator.screen.list.PizzaListFragmentViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.onRemove(pizza);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // clickListener
                com.github.komidawi.pizzacostcalculator.screen.list.PizzaItemListener clickListener = mClickListener;
                // pizza
                com.github.komidawi.pizzacostcalculator.domain.Pizza pizza = mPizza;
                // clickListener != null
                boolean clickListenerJavaLangObjectNull = false;



                clickListenerJavaLangObjectNull = (clickListener) != (null);
                if (clickListenerJavaLangObjectNull) {



                    clickListener.onClick(pizza);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): clickListener
        flag 1 (0x2L): pizza
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}