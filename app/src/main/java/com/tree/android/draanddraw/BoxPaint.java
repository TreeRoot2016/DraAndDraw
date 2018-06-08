package com.tree.android.draanddraw;

import android.graphics.PointF;

/**
 * <pre>
 *     author : tree_root
 *     e-mail : xxx@xx
 *     time   : 2018/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BoxPaint {



    private PointF origin;
    private PointF cru;

    public BoxPaint(PointF origin) {
        this.origin = origin;
        this.cru = origin;
    }

    public PointF getOrigin() {
        return origin;
    }

    public void setOrigin(PointF origin) {
        this.origin = origin;
    }

    public PointF getCru() {
        return cru;
    }

    public void setCru(PointF cru) {
        this.cru = cru;
    }
}
