Ext.define("core.systemmanage.rolemanage.view.AddRoleForm", {
			extend : "Ext.form.Panel",
			alias : "widget.addroleform",
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			align : 'center',
			buttonAlign : 'center',
			buttons : [{
						xtype : "button",
						ref : "addrole",
						iconCls : "table_save",
						text : "增加"
					}, {
						xtype : "button",
						ref : "return",
						iconCls : "return",
						text : "返回"
					}],
			items : [
					{
						xtype : "textfield",
						fieldLabel : "角色名称" + '<font color=red>*</font>',
						labelWidth : 120,
						padding : '5,20,5,30',
						name : "name",
						allowBlank : false,// 不允许为空
						blankText : '角色名称不能为空',// 错误提示内容
						readOnly : false
					}
					],
			initComponent : function() {
				this.callParent(arguments);
			}
		});