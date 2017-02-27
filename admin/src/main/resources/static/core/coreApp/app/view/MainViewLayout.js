/** 系统主程序界面布局类 */
Ext.define("core.app.view.MainViewLayout", {
			extend : 'Ext.panel.Panel',
			border : 0,
			layout : 'border',
			alias : 'widget.mainviewlayout',
			items : [{
						xtype : 'westview',
						region : 'west'
					}, {
						xtype : 'panel',
						region : 'center',
						layout : 'fit',
						// margins : '2 0 0 0',
						border : 0,
						items : [{
									xtype : 'centerview',
									border : 0
								}]
					}],
			dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				height : 50,
				items : [
						"<font size=5 color=#004000><b>服务管理系统</b></font>", {
							xtype : 'tbspacer',
							width : 40
						}, {
							xtype : 'tbtext',
							id : "displaylogin"
						}, {
							xtype : 'tbspacer',
							width : 20
						}, {
							xtype : 'button',
							ref : 'changepassword',
							iconCls : 'password',
							text : '<fone size=5 >修改密码</font>'
						}, {
							xtype : 'tbspacer',
							width : 5
						}, {
							xtype : 'button',
							ref : 'logoutsystem',
							iconCls : 'logout',
							text : '<fone size=5 >退出系统</font>'
						}]
			}],
			initComponent : function() {
				this.callParent(arguments);
			}
});