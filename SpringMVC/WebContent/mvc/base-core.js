MVC = {
	emptyFn : function() {
		Ext.Msg.show({
			title : '提示信息',
			width : 200,
			msg : "<br />此功能暂时未能实现",
			buttons : Ext.Msg.OK,
			icon : Ext.Msg.INFO
		});
	},
	start : 'page.start',
	limit : 'page.limit',
	IP:"",
	YEAR:0,
	MONTH:0,
	getRootIP:function(){
		var	ip="127.0.0.1";
	    return ip;
	}
};

Ext.QuickTips.init();

MVC.Util = function(){
	return {
		applyIf : function(dest, source) {
			for (var p in source) {
				if (typeof dest[p] == "undefined") {// 测试在表单配置是否有此成员
					dest[p] = source[p];// 没有则添加
				} else if (typeof dest[p] == "object" && dest.hasOwnProperty(p)) {// 有则测试其是不是一个对象
					this.applyIf(dest[p], source[p]);
				}
			}
		},
		list : function(o, all) {
			var v = "";
			for (var p in o) {
				if(o.hasOwnProperty(p)){
					v += "属性:" + p + "值:" + o[p]+"\n";
				}
			}
			return v;
		},
		cfg : function(subConfig, baseConfig) {
			if (!subConfig || !baseConfig) {
				alert("在配置属性进行复制的时候,目标与源都不能为空");
			}
			if (!subConfig.init) {
				Risen.Util.applyIf(subConfig, baseConfig);
				subConfig.init = true;
			}
			return subConfig;
		},
		alert: function(msg, cfg) {
			cfg = cfg || {};
			cfg = Ext.isFunction(cfg) ? {cb : cfg} : cfg;
			var base = {
				title : '确认',
				msg : "<br />" + msg,
				width : 250,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO,
				fn : cfg.cb || Ext.emptyFn
			};
			Ext.applyIf(cfg, base);
			Ext.MessageBox.show(cfg);
		},
		msg : function(msg, cfg) {
			cfg = cfg || {};
			cfg = Ext.isFunction(cfg) ? {cb : cfg} : cfg;
			var base = {
				title : '消息',
				msg : "<br />" + msg,
				width : 250,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO,
				fn : cfg.cb || Ext.emptyFn
			};
			Ext.applyIf(cfg, base);
			Ext.MessageBox.show(cfg);
		},
		warn : function(msg, cfg) {
			cfg = cfg || {};
			cfg = Ext.isFunction(cfg) ? {cb : cfg} : cfg;
			var base = {
				title : '警告',
				msg : "<br />" + msg,
				width : 250,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING,
				fn : cfg.cb || Ext.emptyFn
			};
			Ext.applyIf(cfg, base);
			Ext.MessageBox.show(cfg);
		},
		error : function(msg, cfg) {
			cfg = cfg || {};
			cfg = Ext.isFunction(cfg) ? {cb : cfg} : cfg;
			var base = {
				title : '错误',
				msg : "<br />" + msg,
				width : 250,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR,
				fn : cfg.cb || Ext.emptyFn
			};
			Ext.applyIf(cfg, base);
			Ext.MessageBox.show(cfg);
		},
		confirm : function(msg, cfg) {
			cfg = cfg || {};
			cfg = Ext.isFunction(cfg) ? {cb : cfg} : cfg;
			var base = {
                title : "确认",
                msg : msg,
                width : 250,
                buttons: Ext.Msg.YESNO,
                fn: cfg.cb || Ext.emptyFn,
                icon: Ext.Msg.QUESTION
			};
			Ext.applyIf(cfg, base);
			Ext.MessageBox.show(cfg);
		},
		prompt : function(msg, cfg){
			cfg = cfg || {};
			cfg = Ext.isFunction(cfg) ? {cb : cfg} : cfg;
			var base = {
                title : '输入框',
                msg : msg,
                buttons: Ext.Msg.OKCANCEL,
                minWidth:250,
                fn : cfg.cb || Ext.emptyFn,
                prompt:true
            };
            Ext.applyIf(cfg, base);
			Ext.MessageBox.show(cfg);
		},
		each : function(map, fun){
			map = map || {};
			for(var k in map){
				fun(map[k], k);
			}
		},
		/**该方法用来做toolTip用处**/
		toolTip:function(fp){
			  Ext.QuickTips.init();
              Ext.QuickTips.register({
                   target: fp.el,
                   text: fp.toolTip
              })
		}
	}
}();

MVC.each = MVC.Util.each;

MVC.GridContext = function(grid, ctxName){
	this.grid=grid;
	ctxName = ctxName||grid.id
	MVC.GridContext.superclass.constructor.call(this, grid, ctxName);
}