package com.github.komidawi.pizzacostcalculator;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.github.komidawi.pizzacostcalculator.databinding.ActivityMainBindingImpl;
import com.github.komidawi.pizzacostcalculator.databinding.FragmentAddPizzaBindingImpl;
import com.github.komidawi.pizzacostcalculator.databinding.FragmentPizzaListBindingImpl;
import com.github.komidawi.pizzacostcalculator.databinding.ListItemPizzaElementBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTADDPIZZA = 2;

  private static final int LAYOUT_FRAGMENTPIZZALIST = 3;

  private static final int LAYOUT_LISTITEMPIZZAELEMENT = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.github.komidawi.pizzacostcalculator.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.github.komidawi.pizzacostcalculator.R.layout.fragment_add_pizza, LAYOUT_FRAGMENTADDPIZZA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.github.komidawi.pizzacostcalculator.R.layout.fragment_pizza_list, LAYOUT_FRAGMENTPIZZALIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.github.komidawi.pizzacostcalculator.R.layout.list_item_pizza_element, LAYOUT_LISTITEMPIZZAELEMENT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADDPIZZA: {
          if ("layout/fragment_add_pizza_0".equals(tag)) {
            return new FragmentAddPizzaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_add_pizza is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPIZZALIST: {
          if ("layout/fragment_pizza_list_0".equals(tag)) {
            return new FragmentPizzaListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_pizza_list is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEMPIZZAELEMENT: {
          if ("layout/list_item_pizza_element_0".equals(tag)) {
            return new ListItemPizzaElementBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item_pizza_element is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "clickListener");
      sKeys.put(2, "pizza");
      sKeys.put(3, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/activity_main_0", com.github.komidawi.pizzacostcalculator.R.layout.activity_main);
      sKeys.put("layout/fragment_add_pizza_0", com.github.komidawi.pizzacostcalculator.R.layout.fragment_add_pizza);
      sKeys.put("layout/fragment_pizza_list_0", com.github.komidawi.pizzacostcalculator.R.layout.fragment_pizza_list);
      sKeys.put("layout/list_item_pizza_element_0", com.github.komidawi.pizzacostcalculator.R.layout.list_item_pizza_element);
    }
  }
}
