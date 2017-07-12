/**
 * 程序布局放大中间的部分
 */
Ext.define("core.app.view.CenterView", {
    extend: 'Ext.tab.Panel',
    alias: 'widget.centerview',
    id: 'centerid',
    // margins: '2 0 0 0',
    border: 0,
    bodyStyle: 'padding:0px',
    menuAlign: "center",

    plugins: Ext.create('Ext.ux.TabCloseMenu', {
        closeTabText: '关闭所有窗口',
        showCloseOthers: false,
        showCloseAll: false
    }),
    items: [/*,{
     xtype: 'panel',
     //iconCls:'apphome',
     html:'<img src="images/bgimage/sky4.jpg" width=100% height=100%></img>',
     title : '<center height=40>首页</center>',
     }*/],
    initComponent: function () {
        this.callParent(arguments);
    }
});