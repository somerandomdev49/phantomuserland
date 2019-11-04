package phantom.pdbnew.ui;

import java.awt.*;

import org.json.*;

public abstract class UITheme {

    // type_category_mod_name

    // types: color, text, url;

    // color:  java.awt.Color;
    // text:   java.lang.String;
    // url:    java.lang.String;

    // color mod:  bg, fg;
    // text mod:   bold, italic, underline;
    // url mod:    resource, full;

    // categories: notification, main;

    // names:

    protected Color color_notification_bg_inf, color_notification_fg_inf;
    protected Color color_notification_bg_err, color_notification_fg_err;
    protected Color color_notification_bg_wrn, color_notification_fg_wrn;
    protected Color color_notification_bg_scs, color_notification_fg_scs;

    protected String url_icon_resource_inf;
    protected String url_icon_resource_err;
    protected String url_icon_resource_wrn;
    protected String url_icon_resource_scs;
    protected String url_icon_resource_add;

    public Color getColor_notification_bg_inf() {
        return color_notification_bg_inf;
    }

    public Color getColor_notification_fg_inf() {
        return color_notification_fg_inf;
    }

    public Color getColor_notification_bg_err() {
        return color_notification_bg_err;
    }

    public Color getColor_notification_fg_err() {
        return color_notification_fg_err;
    }

    public Color getColor_notification_bg_wrn() {
        return color_notification_bg_wrn;
    }

    public Color getColor_notification_fg_wrn() {
        return color_notification_fg_wrn;
    }

    public Color getColor_notification_bg_scs() {
        return color_notification_bg_scs;
    }

    public Color getColor_notification_fg_scs() {
        return color_notification_fg_scs;
    }

    public String getUrl_icon_resource_inf() {
        return UIUtil.RESOURCES_PATH + url_icon_resource_inf + ".png";
    }

    public String getUrl_icon_resource_err() {
        return UIUtil.RESOURCES_PATH + url_icon_resource_err + ".png";
    }

    public String getUrl_icon_resource_wrn() {
        return UIUtil.RESOURCES_PATH + url_icon_resource_wrn + ".png";
    }

    public String getUrl_icon_resource_scs() {
        return UIUtil.RESOURCES_PATH + url_icon_resource_scs + ".png";
    }

    public String getUrl_icon_resource_add() {
        return UIUtil.RESOURCES_PATH + url_icon_resource_add + ".png";
    }

    private static Color colorFromJSON(JSONObject obj) {
        return new Color(obj.getInt("r"), obj.getInt("g"), obj.getInt("b"));
    }

    public static UITheme fromJSON(String json) {
        JSONObject jsonTheme = new JSONObject(json);
        UITheme theme = new UITheme() {};
        //theme.color_notification_bg_inf = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_inf"))
        theme.color_notification_bg_inf = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_inf"));
        theme.color_notification_fg_inf = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_inf"));
        theme.color_notification_bg_err = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_err"));
        theme.color_notification_fg_err = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_err"));
        theme.color_notification_bg_wrn = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_wrn"));
        theme.color_notification_fg_wrn = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_wrn"));
        theme.color_notification_bg_scs = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_scs"));
        theme.color_notification_fg_scs = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_scs"));
        theme.url_icon_resource_inf = (jsonTheme.getString("url_icon_resource_inf"));
        theme.url_icon_resource_err = (jsonTheme.getString("url_icon_resource_err"));
        theme.url_icon_resource_wrn = (jsonTheme.getString("url_icon_resource_wrn"));
        theme.url_icon_resource_scs = (jsonTheme.getString("url_icon_resource_scs"));
        theme.url_icon_resource_add = (jsonTheme.getString("url_icon_resource_add"));
        return theme;
    }
}
