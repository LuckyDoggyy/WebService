Ext.define("core.systemmanage.rolemanage.view.AddRole", {
			extend : "Ext.form.Panel",
			alias : "widget.addrole",
			title : "<center height=40>添加角色</center>",
			bodyPadding : '15 15 0',
			layout : 'anchor',
			align : 'center',
			buttonAlign : 'center',
			items : [{
						xtype : "textfield",
						fieldLabel : "角色名称" + '<font color=red>*</font>',
						labelWidth : 80,
						anchor : '18%',
						name : "name",
						allowBlank : false,
						blankText : '角色名称不能为空',
						readOnly : false
					},{
						xtype : "button",
						ref : "addrole",
						iconCls : "table_save",
						text : "增加"
					}],
			initComponent : function() {
				this.callParent(arguments);
			}
		});