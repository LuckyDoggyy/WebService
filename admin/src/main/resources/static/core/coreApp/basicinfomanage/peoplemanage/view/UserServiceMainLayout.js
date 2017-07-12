Ext.define("core.basicinfomanage.peoplemanage.view.UserServiceMainLayout", {
    extend: "Ext.panel.Panel",
    alias: 'widget.userservicemainlayout',
    title: "<center height=40>用户调用权限</center>",
    layout: 'border',
    items: [{
        region: 'west',
        title: '西',
        xtype: "panel",
        html: "子元素2",

    }, {
        region: 'center',
        title: '主体',
        xtype: "panel",
        html: "子元素3"
    }]

});