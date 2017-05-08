(function($){
var myflow = $.myflow;

$.extend(true, myflow.editors, {


	/*tableEditor : function(){
		var _props,_k,_div,_src,_r;
    		this.init = function(props, k, div, src, r){
    			_props=props; _k=k; _div=div; _src=src; _r=r;
				var value=_props[_k].value;

				if(value){
					var list=_props[_k].value.split(",");
					var str='';
					for (i in list){
						str+='<tr><td><input value="'+list[i]+'"></td><td><input type="button" value="增加" onclick="add(this)"></td><td><input type="button" value="删除" onclick="remove(this)"></td></tr>';
					}
					$('<table id="table_'+_div+'">'+str+'</table>').appendTo('#'+_div);
				}else{
					$('<table id="table"><tr><td><input style="width:100%;"/></td><td><input type="button" value="增加" onclick="add(this)"></td><td><input type="button" value="删除" onclick="remove(this)"></td></tr></table>').appendTo('#'+_div);
				}

				$('#'+_div).data('editor', this);
    		};

    		this.destroy = function(){
    			var array=new Array();
    			$('#'+_div).find("tr").each(function(){
				   var tdArr = $(this).children();
				   var value = tdArr.eq(0).find("input").val();
				   array.push(value);
				});
				_props[_k].value = array.join(",");
    		}
    	},*/


	inputEditor : function(){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;
			//console.log(props[_k].value);
			$('<input style="width:100%;"/>').val(props[_k].value).change(function(){
				props[_k].value = $(this).val();
			}).appendTo('#'+_div);
			
			$('#'+_div).data('editor', this);
		}
		this.destroy = function(){
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
		}
	},

    multiEditor : function(){
        var _props,_k,_div,_src,_r;
        this.init = function(props, k, div, src, r){
            _props=props; _k=k; _div=div; _src=src; _r=r;
            var value=_props[_k].value.split(',').map(function (x) {
                var arr=x.split(':');
                return {a:arr[0]||'',b:arr[1]||''}
            });
            console.log(value);
            newRow=function (a,b) {
                a=a||'';
                b=b||'';
                var input1=$('<td><input style="width: 50px;" type="text" name="a"  value="'+a+'" /></td>');
                var input2=$('<td><input style="width: 50px;" type="text" name="b"  value="'+b+'" /></td>');

                var tr=$('<tr></tr>');
                $(tr).append(input1);
                $(tr).append(input2);
                $(tr).append('<td><input type="button" value="添加" onclick="newRow()" /></td>');
                $(tr).append('<td><input type="button" value="删除" onclick="remove(this)"/></td>');
                $('#'+_div).find('table').append($(tr));
            }


            var table=$('<table></table>');
            $(table).appendTo('#'+_div);
            if(value.length==0) value=[{a:'',b:''}];
            for(var i=0;i<value.length;i++){
                newRow(value[i].a,value[i].b);
            }
            $('#'+_div).data('editor', this);
        }
        this.destroy = function(){
            var array=new Array();
            $('#'+_div).find("table").find('tr').each(function(){
                var a=$(this).find('input[name="a"]').val();
                var b=$(this).find('input[name="b"]').val();
                array.push(a+':'+b);
            });
            _props[_k].value = array.join(",");
        }
    },

    /*multiEditor : function(){
        var _props,_k,_div,_src,_r;
        thsi.init = function(props, k, div, src, r){
            _props=props; _k=k; _div=div; _src=src; _r=r;
            //console.log(props[_k].value);
            var table=$('<table></table>');
					var tr = $('<tr></tr>>');
					$.each(_props[_k].value, function(key,value) {
                        $('<td><input type="text" style="width:100%;" id="paramName" /></td>').val(key).change(function () {
                            key = $(this).val();
                        }).appendTo($(tr));
                        $('<td><input type="text" style="width:100%;" id="paramValue" /></td>').val(value).change(function () {
                            value = $(this).val();
                        }).appendTo($(tr));
                        $('#' + _props).find('table').append($(tr));
                        /!*$('<td><input type="submit" onclick="" /></td><td><input type="submit" onclick="remove(this)"></td>').appendTo($(tr));
                    *!/});



            $(table).appendTo('#'+_div);
            allTr();
            $('#'+_div).data('editor', this);
        }
        this.destroy = function(){
            $('#'+_div+' input').each(function(){
                _props[_k].value = $(this).val();
            });
        }
	},*/



	inputAreaEditor : function(){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;
			$('<textarea style="width:100%;"/>').val(props[_k].value).change(function(){
				props[_k].value = $(this).val();
			}).appendTo('#'+_div);

			$('#'+_div).data('editor', this);
		}
		this.destroy = function(){
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
		}
	},
	selectEditor : function(arg){
		var _props,_k,_div,_src,_r;
		this.init = function(props, k, div, src, r){
			_props=props; _k=k; _div=div; _src=src; _r=r;

			var sle = $('<select  style="width:100%;"/>').val(props[_k].value).change(function(){
				props[_k].value = $(this).val();
			}).appendTo('#'+_div);
			
			if(typeof arg === 'string'){
				$.ajax({
				   type: "GET",
				   url: arg,
				   success: function(data){
					  var opts = eval(data);
					 if(opts && opts.length){
						for(var idx=0; idx<opts.length; idx++){
							sle.append('<option value="'+opts[idx].value+'">'+opts[idx].name+'</option>');
						}
						sle.val(_props[_k].value);
					 }
				   }
				});
			}else {
				for(var idx=0; idx<arg.length; idx++){
					sle.append('<option value="'+arg[idx].value+'">'+arg[idx].name+'</option>');
				}
				sle.val(_props[_k].value);
			}
			
			$('#'+_div).data('editor', this);
		};
		this.destroy = function(){
			$('#'+_div+' input').each(function(){
				_props[_k].value = $(this).val();
			});
		};
	},



});

})(jQuery);