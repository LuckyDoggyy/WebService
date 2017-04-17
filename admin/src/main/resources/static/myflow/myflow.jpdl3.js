(function($){
var myflow = $.myflow;
var services;
$.ajax({
        url:'../ws/getWsOption',
        type:'POST',
        async:false,
        timeout:5000,
        dataType:'json',
        success:function(data){
            console.log(data);
            services=data;
        },
        error:function(xhr,textStatus){
            alert("请求失败");
        }
        })


$.extend(true,myflow.config.props.props,{
//TODO 展示bug
	name : {name:'name', label:'名称', value:'新建流程', editor:function(){return new myflow.editors.inputEditor();}},
	flowid : {name:'flowid', label:'标识', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}}
});

$.extend(true,myflow.config.tools.states,{
	start : {
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'开始'},
				img : {src : 'img/16/start_event_empty.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'开始'}
				}},
			end : {type : 'end',
				name : {text:'<<end>>'},
				text : {text:'结束'},
				img : {src : 'img/16/end_event_terminate.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'结束'}
				}},
			'end-cancel' : {type : 'end-cancel',
				name : {text:'<<end-cancel>>'},
				text : {text:'取消'},
				img : {src : 'img/16/end_event_cancel.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'取消'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			'end-error' : {type : 'end-error',
				name : {text:'<<end-error>>'},
				text : {text:'错误'},
				img : {src : 'img/16/end_event_error.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'错误'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			state : {type : 'state',
				name : {text:'<<state>>'},
				text : {text:'状态'},
				img : {src : 'img/16/task_empty.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'状态'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			fork : {type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'分支'},
				img : {src : 'img/16/gateway_parallel.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'分支'},
					judge: {name:'judge', label: '判断', value:'', editor: function(){return new myflow.editors.inputEditor();}}
				}},
			join : {type : 'join',
				name : {text:'<<join>>'},
				text : {text:'合并'},
				img : {src : 'img/16/gateway_parallel.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'合并'},
					temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}}
				}},
			task : {type : 'task',
				name : {text:'<<task>>'},
				text : {text:'任务'},
				img : {src : 'img/16/task_empty.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'任务', editor: function(){return new myflow.editors.textEditor();}},
					taskremark: {name:'taskremark', label: '任务注释', value:'', editor: function(){return new myflow.editors.inputAreaEditor();}},
					taskcategory: {name:'taskcategory', label : '任务类别', value:'', editor: function(){return new myflow.editors.selectEditor(services);}}
				}}
});
})(jQuery);