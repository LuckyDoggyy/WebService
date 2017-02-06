/**
 * 程序开始执行的位置 相当于window.onload=function(){}
 */
// 初始化Ext.QuickTips，以使得tip提示可用
Ext.QuickTips.init();
// 初始化提示工具框
Ext.tip.QuickTipManager.init();
Ext.Loader.setConfig({
    enabled : true
});

Ext.Loader.setPath('Ext.ux', 'core/ux');
Ext.onReady(function() {
    new Ext.KeyMap(document, {
        key : 8,
        fn : function(obj, e) {
            var type = e.target.type;
            var readonly = e.target.readOnly;
            if (type != 'text' && type != 'textarea'
                && type != 'password') {
                // e.preventDefault();
                e.stopEvent();
            } else if (readonly) {
                // e.preventDefault();
                e.stopEvent();
            }
        },
        scope : this
    });
    /** 开始执行应用程序 */
    Ext.application({
        name : "core",// 命名空间core.view.LoginWindow
        appFolder : "core/coreApp",//
        launch : function() {
            Ext.create("Ext.container.Viewport", {
                layout : "fit",
                border : 0,
                items : [{
                    xtype : "mainviewlayout"
                }]
            });
        },
        controllers : ["core.app.controller.MainController"]
    });
});
