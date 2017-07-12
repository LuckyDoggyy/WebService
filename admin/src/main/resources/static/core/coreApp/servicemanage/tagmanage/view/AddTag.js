Ext.define("core.servicemanage.tagmanage.view.AddTag",
    {
        extend: "Ext.form.Panel",
        alias: "widget.addtag",
        title: "<center height=40>分类添加</center>",
        bodyPadding: '5 5 0',
        width: 150,
        height: 300,
        buttonAlign: 'center',
        fieldDefaults: {
            labelAlign: 'left',
            msgTarget: 'side'
        },
        layout: 'anchor',
        items: [{
            xtype: 'textfield',
            fieldLabel: '分类名' + '<font color=red>*</font>',
            anchor: '18%',
            labelWidth: 80,
            name: 'tagName',
            allowBlank: false,
            blankText: '分类名不能为空'
        }, {
            xtype: 'textfield',
            fieldLabel: '排序',
            anchor: '18%',
            labelWidth: 80,
            name: 'orderIndex',
            allowBlank: true
        }, {
            xtype: 'button',
            text: '添加',
            ref: 'addTag',
            iconCls: "table_save"
        }]
    });