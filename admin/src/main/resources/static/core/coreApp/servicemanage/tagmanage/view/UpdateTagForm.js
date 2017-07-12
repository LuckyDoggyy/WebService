Ext.define("core.servicemanage.tagmanage.view.UpdateTagForm", {
    extend: "Ext.form.Panel",
    alias: "widget.updatetagform",
    mixins: {
        suppleUtil: "core.util.SuppleUtil"
    },
    layout: 'anchor',
    defaults: {
        anchor: '100%'
    },
    align: 'center',
    buttonAlign: 'center',
    items: [{
        xtype: 'textfield',
        fieldLabel: '自增id',
        labelWidth: 80,
        name: 'autoId',
        hidden: true
    }, {
        xtype: 'textfield',
        fieldLabel: '分类名' + '<font color=red>*</font>',
        labelWidth: 80,
        name: 'tagName',
        allowBlank: false,
        blankText: '分类名不能为空'
    }, {
        xtype: 'textfield',
        fieldLabel: '排序',
        labelWidth: 80,
        name: 'orderIndex',
        allowBlank: true
    }],
    buttons: [{
        text: '修改',
        ref: 'updateTag',
        iconCls: "table_save"
    }]
});