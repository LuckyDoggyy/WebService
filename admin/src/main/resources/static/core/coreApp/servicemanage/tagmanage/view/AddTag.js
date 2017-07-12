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
            fieldLabel: '父分类',
            xtype: "combobox",
            name: 'pid',
            anchor: '18%',
            labelWidth: 80,
            emptyText: '请选择分类',
            queryMode: 'remote',
            store: "core.servicemanage.tagmanage.store.TagOptStore",
            loadingText: '正在加载数据，请稍侯……',
            triggerAction: 'all',
            valueField: 'value',
            forceSelection: true,
            allowBlank: false,
            displayField: 'name'
        }, {
            xtype: 'button',
            text: '添加',
            ref: 'addTag',
            iconCls: "table_save"
        }]
    });