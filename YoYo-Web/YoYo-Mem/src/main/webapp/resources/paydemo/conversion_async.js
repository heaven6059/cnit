(function(){var f=this,k=function(a){var b=typeof a;if("object"==b)if(a){if(a instanceof Array)return"array";if(a instanceof Object)return b;var e=Object.prototype.toString.call(a);if("[object Window]"==e)return"object";if("[object Array]"==e||"number"==typeof a.length&&"undefined"!=typeof a.splice&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("splice"))return"array";if("[object Function]"==e||"undefined"!=typeof a.call&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("call"))return"function"}else return"null";
else if("function"==b&&"undefined"==typeof a.call)return"object";return b};var l=parseFloat("0.01"),m=isNaN(l)||1<l||0>l?0:l;var n=/^true$/.test("true")?!0:!1;var p=function(a){p[" "](a);return a};p[" "]=function(){};var t=function(a,b){for(var e in a)Object.prototype.hasOwnProperty.call(a,e)&&b.call(void 0,a[e],e,a)};var u=window;var A;a:{var B=f.navigator;if(B){var C=B.userAgent;if(C){A=C;break a}}A=""}var D=function(a){return-1!=A.indexOf(a)};var H=D("Opera")||D("OPR"),I=D("Edge")||D("Trident")||D("MSIE"),J=D("Gecko")&&!(-1!=A.toLowerCase().indexOf("webkit")&&!D("Edge"))&&!(D("Trident")||D("MSIE"))&&!D("Edge"),aa=-1!=A.toLowerCase().indexOf("webkit")&&!D("Edge"),ba=function(){var a=A;if(J)return/rv\:([^\);]+)(\)|;)/.exec(a);if(I&&D("Edge"))return/Edge\/([\d\.]+)/.exec(a);if(I)return/\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/.exec(a);if(aa)return/WebKit\/(\S+)/.exec(a)};
(function(){if(H&&f.opera){var a=f.opera.version;return"function"==k(a)?a():a}var a="",b=ba();b&&(a=b?b[1]:"");return I&&!D("Edge")&&(b=(b=f.document)?b.documentMode:void 0,b>parseFloat(a))?String(b):a})();var ca=function(a){this.b=[];this.a={};for(var b=0,e=arguments.length;b<e;++b)this.a[arguments[b]]=""},da=function(){var a=K,b=a.b.concat([]);t(a.a,function(a){""!=a&&b.push(a)});return b};var K,L="google_conversion_id google_conversion_format google_conversion_type google_conversion_order_id google_conversion_language google_conversion_value google_conversion_currency google_conversion_domain google_conversion_label google_conversion_color google_disable_viewthrough google_remarketing_only google_remarketing_for_search google_conversion_items google_custom_params google_conversion_date google_conversion_time google_conversion_js_version onload_callback opt_image_generator google_is_call google_conversion_page_url".split(" ");
function M(a){return null!=a?escape(a.toString()):""}function T(a){return null!=a?a.toString().substring(0,512):""}function U(a,b){var e=M(b);if(""!=e){var c=M(a);if(""!=c)return"&".concat(c,"=",e)}return""}function V(a){var b=typeof a;return null==a||"object"==b||"function"==b?null:String(a).replace(/,/g,"\\,").replace(/;/g,"\\;").replace(/=/g,"\\=")}
function ea(a){var b;if((a=a.google_custom_params)&&"object"==typeof a&&"function"!=typeof a.join){var e=[];for(b in a)if(Object.prototype.hasOwnProperty.call(a,b)){var c=a[b];if(c&&"function"==typeof c.join){for(var d=[],g=0;g<c.length;++g){var h=V(c[g]);null!=h&&d.push(h)}c=0==d.length?null:d.join(",")}else c=V(c);(d=V(b))&&null!=c&&e.push(d+"="+c)}b=e.join(";")}else b="";return""==b?"":"&".concat("data=",encodeURIComponent(b))}
function W(a){return"number"!=typeof a&&"string"!=typeof a?"":M(a.toString())}function fa(a){if(!a)return"";a=a.google_conversion_items;if(!a)return"";for(var b=[],e=0,c=a.length;e<c;e++){var d=a[e],g=[];d&&(g.push(W(d.value)),g.push(W(d.quantity)),g.push(W(d.item_id)),g.push(W(d.adwords_grouping)),g.push(W(d.sku)),b.push("("+g.join("*")+")"))}return 0<b.length?"&item="+b.join(""):""}
function ga(a,b,e){var c=[];if(a){var d=a.screen;d&&(c.push(U("u_h",d.height)),c.push(U("u_w",d.width)),c.push(U("u_ah",d.availHeight)),c.push(U("u_aw",d.availWidth)),c.push(U("u_cd",d.colorDepth)));a.history&&c.push(U("u_his",a.history.length))}e&&"function"==typeof e.getTimezoneOffset&&c.push(U("u_tz",-e.getTimezoneOffset()));b&&("function"==typeof b.javaEnabled&&c.push(U("u_java",b.javaEnabled())),b.plugins&&c.push(U("u_nplug",b.plugins.length)),b.mimeTypes&&c.push(U("u_nmime",b.mimeTypes.length)));
return c.join("")}function X(a,b,e){var c="";if(b){var d;if(a.top==a)d=0;else{var g=a.location.ancestorOrigins;if(g)d=g[g.length-1]==a.location.origin?1:2;else{g=a.top;try{var h;if(h=!!g&&null!=g.location.href)c:{try{p(g.foo);h=!0;break c}catch(q){}h=!1}d=h}catch(v){d=!1}d=d?1:2}}h="";h=e?e:1==d?a.top.location.href:a.location.href;c+=U("frm",d);c+=U("url",T(h));c+=U("ref",T(b.referrer))}return c}
function Y(a){var b;K?(b=K,b=b.a.hasOwnProperty(2)?b.a[2]:""):b="";return"42631044"==b||a&&a.location&&a.location.protocol&&"https:"==a.location.protocol.toString().toLowerCase()?"https:":"http:"}var ha=/Android ([01]\.|2\.[01])/i;function ia(){return new Image}function Z(a,b,e){var c=ia;"function"===typeof a.opt_image_generator&&(c=a.opt_image_generator);c=c();b+=U("async","1");c.src=b;c.onload=e&&"function"===typeof a.onload_callback?a.onload_callback:function(){}}
function ja(a){for(var b=window,e={},c=function(c){e[c]=a&&null!=a[c]?a[c]:b[c]},d=0;d<L.length;d++)c(L[d]);c("onload_callback");return e};window.google_trackConversion=function(a){a=ja(a);a.google_conversion_format=3;var b;var e=window,c=navigator,d=document,g=!1;if(a&&3==a.google_conversion_format){try{var h;if("landing"==a.google_conversion_type||!a.google_conversion_id||a.google_remarketing_only&&a.google_disable_viewthrough)h=!1;else{a.google_conversion_date=new Date;a.google_conversion_time=a.google_conversion_date.getTime();a.google_conversion_snippets="number"==typeof a.google_conversion_snippets&&0<a.google_conversion_snippets?
a.google_conversion_snippets+1:1;"number"!=typeof a.google_conversion_first_time&&(a.google_conversion_first_time=a.google_conversion_time);a.google_conversion_js_version="7";0!=a.google_conversion_format&&1!=a.google_conversion_format&&2!=a.google_conversion_format&&3!=a.google_conversion_format&&(a.google_conversion_format=1);K=new ca(1,2,3);if("https:"!=Y(u)&&!n&&!ha.test(navigator.userAgent)&&K){var q=K,v=["42631043","42631044"];if(q.a.hasOwnProperty(2)&&""==q.a[2]){var E,F,G;c:{try{var N=window.top.location.hash;
if(N){var O=N.match(/\bdeid=([\d,]+)/);G=O&&O[1]||"";break c}}catch(la){}G=""}var P=G.match(new RegExp("\\b("+v.join("|")+")\\b"));F=P&&P[0]||null;var w;if(F)w=F;else c:{if(!(1E-4>Math.random())){var x=Math.random();if(x<m){try{var Q=new Uint16Array(1);window.crypto.getRandomValues(Q);x=Q[0]/65536}catch(ma){x=Math.random()}w=v[Math.floor(x*v.length)];break c}}w=null}(E=w)&&""!=E&&q.a.hasOwnProperty(2)&&(q.a[2]=E)}}h=!0}if(h){h="/?";"landing"==a.google_conversion_type&&(h="/extclk?");var R;R=Y(e)+
"//"+(a.google_remarketing_only?"googleads.g.doubleclick.net":a.google_conversion_domain||"www.googleadservices.com")+"/pagead/"+[a.google_remarketing_only?"viewthroughconversion/":"conversion/",M(a.google_conversion_id),h,"random=",M(a.google_conversion_time)].join("");var ka=d?{visible:1,hidden:2,prerender:3,preview:4}[d.webkitVisibilityState||d.mozVisibilityState||d.visibilityState||""]||0:"0",y;b:{var S=a.google_conversion_language;if(null!=S){var r=S.toString();if(2==r.length){y=U("hl",r);break b}if(5==
r.length){y=U("hl",r.substring(0,2))+U("gl",r.substring(3,5));break b}}y=""}b=[U("cv",a.google_conversion_js_version),U("fst",a.google_conversion_first_time),U("num",a.google_conversion_snippets),U("fmt",a.google_conversion_format),U("value",a.google_conversion_value),U("currency_code",a.google_conversion_currency),U("label",a.google_conversion_label),U("oid",a.google_conversion_order_id),U("bg",a.google_conversion_color),y,U("guid","ON"),U("disvt",a.google_disable_viewthrough),U("is_call",a.google_is_call),
U("eid",da().join()),fa(a),ga(e,c,a.google_conversion_date),ea(a),X(e,d,a.google_conversion_page_url),a.google_remarketing_for_search&&!a.google_conversion_domain?"&srr=n":"",U("vis",ka)].join("");Z(a,R+b,!0);if(a.google_remarketing_for_search&&!a.google_conversion_domain){var z;z=Y(e)+"//www.google.com/ads/user-lists/"+[M(a.google_conversion_id),"/?random=",Math.floor(1E9*Math.random())].join("");z+=[U("label",a.google_conversion_label),U("fmt","3"),X(e,d,a.google_conversion_page_url)].join("");
Z(a,z,!1)}g=!0}}catch(na){}b=g}else b=!1;return b};})();