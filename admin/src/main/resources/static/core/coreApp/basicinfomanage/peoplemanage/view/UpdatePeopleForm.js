Ext.define("core.kaoqinmanager.peoplemanage.view.UpdatePeopleForm", {
    extend: "Ext.form.Panel",
    alias: "widget.updatepeopleform",
    bodyPadding: '5 5 0',
    width: 600,
    layout: 'anchor',
    defaults: {
        anchor: '100%'
    },
    align: 'center',
    buttonAlign: 'center',
    items: [{
        xtype: 'textfield',
        fieldLabel: 'uid',
        name: 'uid',
        hidden: true
    }, {
        xtype: 'textfield',
        fieldLabel: '用户名' + '<font color=red>*</font>',
        labelWidth: 50,
        name: 'account',
        disabled: true
    }, {
        xtype: 'textfield',
        fieldLabel: '昵称',
        labelWidth: 50,
        name: 'nickName',
        allowBlank: true
    }, {
        xtype: 'textarea',
        fieldLabel: '备注',
        labelWidth: 50,
        width: 230,
        name: 'remarks',
        allowBlank: true
    }],
    buttons: [{
        text: '修改',
        ref: 'updatePeople',
        iconCls: "table_save"
    }]
});