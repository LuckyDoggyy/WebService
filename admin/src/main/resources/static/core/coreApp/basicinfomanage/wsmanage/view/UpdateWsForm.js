Ext.define("core.basicinfomanage.wsmanage.view.UpdateWsForm", {
			extend : "Ext.form.Panel",
			alias : "widget.updatewsform",
			mixins : {
				suppleUtil : "core.util.SuppleUtil"
			},
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			align : 'center',
			buttonAlign : 'center',
			items : [{
						xtype : 'textfield',
						fieldLabel : 'sid',
						name : 'sid',
						hidden : true
					},{
						xtype : 'textfield',
						fieldLabel : '服务名',
						labelWidth: 70,
						name : 'serviceName',
						allowBlank : false,
						blankText : '服务名不能为空'
					},{
						xtype : 'textfield',
						fieldLabel : '请求地址',
						labelWidth: 70,
						name : 'url',
						allowBlank : false
					},{
						xtype : 'textfield',
						fieldLabel : 'wsdl地址',
						labelWidth: 70,
						name : 'wsdlUrl',
						allowBlank : false
					},{
						xtype : 'textfield',
						fieldLabel : '命名空间',
						labelWidth: 70,
						name : 'targetNamespace',
						allowBlank : false
					},{
						xtype : 'textfield',
						fieldLabel : '请求方法',
						labelWidth: 70,
						name : 'method',
						allowBlank : false
					},{
						xtype : 'textarea',
						fieldLabel : '备注',
						labelWidth: 70,
						 width: 230,
						name : 'remark',
						allowBlank : true
					},{
						xtype : 'textarea',
						fieldLabel : '输出配置',
						style:"wrap:soft;",
						labelWidth: 70,
						width: 280,
						name : 'output',
						allowBlank : true
					}],
			buttons : [{
						text : '修改',
						ref : 'updateWs',
						iconCls : "table_save"
					}]
		});