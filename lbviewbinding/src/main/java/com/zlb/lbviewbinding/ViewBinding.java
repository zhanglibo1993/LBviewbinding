package com.zlb.lbviewbinding;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.zlb.lbviewbinding.anno.BindView;
import com.zlb.lbviewbinding.anno.ContentView;
import com.zlb.lbviewbinding.anno.EventBase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * create by Grant on 2019/5/8.
 */
public class ViewBinding {
    private ViewBinding() {

    }

    public static void init(Context context) {
//        injectLayout(context);
        injectViews(context);
        injectEvents((Activity) context);
    }

    private static void injectEvents(Activity activity) {
        Class<?> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<?> anntionType = annotation.annotationType();
                EventBase eventBase = anntionType.getAnnotation(EventBase.class);
                if (eventBase != null) {
                    String listenerSetter = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String methodName = eventBase.callBackMethod();

                    try {
                        Method aMethod = anntionType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) aMethod.invoke(annotation, null);

                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[]{listenerType}, handler);
                        for (int viewId : viewIds) {
                            View view = activity.findViewById(viewId);
                            Method setEventListenerMethod = view.getClass().getMethod(listenerSetter, listenerType);
                            setEventListenerMethod.invoke(view, listener);
                        }
                    } catch (Exception e) {

                    }
                }

            }
        }
    }

    public static void initWithSetcontentView(Context context) {
        injectLayout(context);
        init(context);
    }


    private static void injectViews(Context context) {
        Class<?> clazz = context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                int viewId = bindView.value();

                try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    View view = (View) method.invoke(context, viewId);
                    field.setAccessible(true);
                    field.set(context, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private static void injectLayout(Context context) {
        Class<?> clazz = context.getClass();
        int layoutId = 0;
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            layoutId = contentView.value();

            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(context, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
