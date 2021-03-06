Ext.define("core.basicinfomanage.wsmanage.view.WsCallForm", {
    extend: "Ext.form.Panel",
    alias: "widget.wscallform",
    mixins: {
        suppleUtil: "core.util.SuppleUtil"
    },
    layout: 'anchor',
    align: 'center',
    buttonAlign: 'center',
    items: [{
        xtype: 'textfield',
        fieldLabel: 'sid',
        name: 'sid',
        hidden: true
    }, {
        xtype: 'textfield',
        fieldLabel: '服务名',
        labelWidth: 70,
        width: 350,
        name: 'serviceName',
        allowBlank: false,
        blankText: '服务名不能为空'
    }, {
        xtype: 'textfield',
        fieldLabel: '请求地址',
        labelWidth: 70,
        width: 350,
        name: 'url',
        allowBlank: false
    }, {
        xtype: 'textfield',
        fieldLabel: 'wsdl地址',
        labelWidth: 70,
        width: 350,
        name: 'wsdlUrl',
        allowBlank: false
    }, {
        xtype: 'textfield',
        fieldLabel: '命名空间',
        labelWidth: 70,
        width: 350,
        name: 'targetNamespace',
        allowBlank: false
    }, {
        xtype: 'textfield',
        fieldLabel: '请求方法',
        labelWidth: 70,
        name: 'method',
        allowBlank: false
    }, {
        xtype: 'textarea',
        fieldLabel: '备注',
        labelWidth: 70,
        width: 350,
        name: 'remark',
        allowBlank: true
    }, {
        xtype: 'textarea',
        fieldLabel: '输出配置',
        labelWidth: 70,
        height: 110,
        width: 350,
        name: 'output',
        allowBlank: true
    }, {
        xtype: 'button',
        text: '调用',
        ref: 'call',
        width: 60,
        iconCls: "table_save"
    }]
});