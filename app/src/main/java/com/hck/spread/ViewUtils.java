package com.hck.spread;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class ViewUtils {
    public static final int POS_TOP = 2;
    public static void setCompoundDrawables(Context context, TextView view, int type, int id) {
        if (context != null) {
            try {
                setCompoundDrawables(context, view, type, context.getResources().getDrawable(id));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }
    }

    private static void setCompoundDrawables(Context context, TextView view, int type, Drawable drawable) {
        if (context != null) {
            try {
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                }

                switch (type) {
                    case 1:
                        view.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                        break;
                    case 2:
                        view.setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
                        break;
                    case 3:
                        view.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, drawable);
                        break;
                    case 4:
                        view.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
                }
            } catch (Exception var5) {
                var5.printStackTrace();
            }

        }
    }
}
