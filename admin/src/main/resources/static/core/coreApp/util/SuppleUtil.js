Ext.define("core.util.SuppleUtil", {
			/**
			 * 同步请求Ajax
			 * 
			 * @param {}
			 *            config
			 * @return {}
			 */
			ajax : function(config) {
				var data = {};
				var request = {
					method : "POST",
					timeout: 160000,
					async : false,
					success : function(response) {
						// console.log(response);
						data = Ext.decode(Ext.valueFrom(response.responseText,
								'{}'));
					},
					failure : function(response) {
						alert('数据请求出错了！！！！\n错误信息：\n');
					}
				};
				var request2 = request;
				var request = Ext.apply(request2, config);
				Ext.Ajax.request(request2);
				return data;
			}
		});