(function(){"use strict";var e={4120:function(e,t,l){var a=l(3751),n=l(791),r=(l(4188),l(641)),o=(l(8992),l(2577),l(33)),u=l(953),i=l(163);l(4114),l(4520),l(3949);const s=["src","alt"],c={style:{padding:"14px"}};var d={__name:"ImageGrid",props:{images:{type:Array,required:!0}},emits:["deleteImages"],setup(e,{emit:t}){const l=e,a=t,n=(0,u.KR)([]),i=e=>{e.selected?n.value.push(e.id):n.value=n.value.filter((t=>t!==e.id))},d=()=>{a("deleteImages",n.value),n.value=[]};return(0,r.wB)((()=>l.images),(e=>{e.forEach((e=>{Object.hasOwn(e,"selected")||(e.selected=!1)}))}),{deep:!0,immediate:!0}),(t,l)=>{const a=(0,r.g2)("el-button"),u=(0,r.g2)("el-col"),f=(0,r.g2)("el-row"),g=(0,r.g2)("el-checkbox"),p=(0,r.g2)("el-card");return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.bF)(f,{gutter:20,class:"mb-4"},{default:(0,r.k6)((()=>[(0,r.bF)(u,{span:24},{default:(0,r.k6)((()=>[(0,r.bF)(a,{type:"danger",onClick:d,disabled:0===n.value.length},{default:(0,r.k6)((()=>[(0,r.eW)(" 删除选中图片 ("+(0,o.v_)(n.value.length)+") ",1)])),_:1},8,["disabled"])])),_:1})])),_:1}),(0,r.bF)(f,{gutter:20},{default:(0,r.k6)((()=>[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(e.images,(e=>((0,r.uX)(),(0,r.Wv)(u,{xs:24,sm:12,md:8,lg:6,key:e.id,class:"mb-4"},{default:(0,r.k6)((()=>[(0,r.bF)(p,{"body-style":{padding:"0px"}},{default:(0,r.k6)((()=>[(0,r.bF)(g,{modelValue:e.selected,"onUpdate:modelValue":t=>e.selected=t,onChange:t=>i(e),class:"image-checkbox"},null,8,["modelValue","onUpdate:modelValue","onChange"]),(0,r.Lk)("img",{src:e.url,alt:e.title,class:"image"},null,8,s),(0,r.Lk)("div",c,[(0,r.Lk)("span",null,(0,o.v_)(e.title),1)])])),_:2},1024)])),_:2},1024)))),128))])),_:1})])}}},f=l(6262);const g=(0,f.A)(d,[["__scopeId","data-v-f3e56210"]]);var p=g,m={__name:"SidebarMenu",props:{galleries:Array,currentGalleryId:Number},emits:["selectGallery"],setup(e,{emit:t}){const l=t,a=e=>{l("selectGallery",e)};return(t,l)=>{const n=(0,r.g2)("el-menu-item"),u=(0,r.g2)("el-menu");return(0,r.uX)(),(0,r.Wv)(u,{"default-active":e.currentGalleryId.toString(),class:"el-menu-vertical-demo"},{default:(0,r.k6)((()=>[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(e.galleries,(e=>((0,r.uX)(),(0,r.Wv)(n,{key:e.id,index:e.id.toString(),onClick:t=>a(e.id)},{default:(0,r.k6)((()=>[(0,r.Lk)("span",null,(0,o.v_)(e.name),1)])),_:2},1032,["index","onClick"])))),128))])),_:1},8,["default-active"])}}};const v=m;var b=v;const h={class:"text-2xl font-bold"};var k={__name:"ImageGallery",setup(e){const t=(0,u.KR)([]),l=(0,u.KR)(0),n=(0,u.KR)(!0),s=(0,u.KR)([{id:0,name:"默认图库"},{id:1,name:"图库 1"},{id:2,name:"图库 2"}]),c=(0,r.EW)((()=>{const e=s.value.find((e=>e.id===l.value));return e?e.name:""})),d=()=>{n.value=!n.value},f=e=>{l.value=e,g()},g=async()=>{try{const e=await fetch(`http://localhost:8080/gallery/${l.value}`);if(!e.ok)throw new Error("Failed to fetch images");t.value=await e.json()}catch(e){console.error("Error fetching images:",e),i.nk.error("获取图片失败,请重试。")}},m=()=>{i.nk.success("图片上传成功!"),g()},v=e=>{console.error("Error uploading image:",e),i.nk.error("图片上传失败,请重试。")},k=async e=>{try{const t=await fetch("http://localhost:8080/images",{method:"DELETE",headers:{"Content-Type":"application/json"},body:JSON.stringify({ids:e})});if(!t.ok)throw new Error("Failed to delete images");i.nk.success("图片删除成功!"),await g()}catch(t){console.error("Error deleting images:",t),i.nk.error("图片删除失败,请重试。")}};return(0,r.sV)((()=>{g()})),(e,u)=>{const i=(0,r.g2)("el-aside"),g=(0,r.g2)("el-button"),y=(0,r.g2)("el-col"),_=(0,r.g2)("el-upload"),w=(0,r.g2)("el-row"),F=(0,r.g2)("el-main"),x=(0,r.g2)("el-container");return(0,r.uX)(),(0,r.Wv)(x,null,{default:(0,r.k6)((()=>[(0,r.bo)((0,r.bF)(i,{width:"250px"},{default:(0,r.k6)((()=>[(0,r.bF)(b,{galleries:s.value,currentGalleryId:l.value,onSelectGallery:f},null,8,["galleries","currentGalleryId"])])),_:1},512),[[a.aG,n.value]]),(0,r.bF)(F,null,{default:(0,r.k6)((()=>[(0,r.bF)(g,{onClick:d,type:"primary",icon:"el-icon-menu",circle:"",class:"sidebar-toggle"}),(0,r.bF)(w,{gutter:20,class:"mb-4"},{default:(0,r.k6)((()=>[(0,r.bF)(y,{span:12},{default:(0,r.k6)((()=>[(0,r.Lk)("h1",h,(0,o.v_)(c.value),1)])),_:1}),(0,r.bF)(y,{span:12,class:"text-right"},{default:(0,r.k6)((()=>[(0,r.bF)(_,{action:"http://localhost:8080/images",data:{galleryId:l.value},"on-success":m,"on-error":v,multiple:"","show-file-list":!1},{default:(0,r.k6)((()=>[(0,r.bF)(g,{type:"primary",icon:"el-icon-upload"},{default:(0,r.k6)((()=>u[0]||(u[0]=[(0,r.eW)("上传图片")]))),_:1})])),_:1},8,["data"])])),_:1})])),_:1}),(0,r.bF)(p,{images:t.value,onDeleteImages:k},null,8,["images"])])),_:1})])),_:1})}}};const y=(0,f.A)(k,[["__scopeId","data-v-1b1518f8"]]);var _=y;const w={class:"container mx-auto px-4 py-8"};var F={__name:"App",setup(e){return(e,t)=>((0,r.uX)(),(0,r.CE)("div",w,[t[0]||(t[0]=(0,r.Lk)("h1",{class:"text-4xl font-bold text-center mb-8"},"Modern Image Gallery",-1)),(0,r.bF)(_)]))}};const x=F;var E=x;const I=(0,a.Ef)(E);I.use(n.A),I.mount("#app")}},t={};function l(a){var n=t[a];if(void 0!==n)return n.exports;var r=t[a]={exports:{}};return e[a].call(r.exports,r,r.exports,l),r.exports}l.m=e,function(){var e=[];l.O=function(t,a,n,r){if(!a){var o=1/0;for(c=0;c<e.length;c++){a=e[c][0],n=e[c][1],r=e[c][2];for(var u=!0,i=0;i<a.length;i++)(!1&r||o>=r)&&Object.keys(l.O).every((function(e){return l.O[e](a[i])}))?a.splice(i--,1):(u=!1,r<o&&(o=r));if(u){e.splice(c--,1);var s=n();void 0!==s&&(t=s)}}return t}r=r||0;for(var c=e.length;c>0&&e[c-1][2]>r;c--)e[c]=e[c-1];e[c]=[a,n,r]}}(),function(){l.d=function(e,t){for(var a in t)l.o(t,a)&&!l.o(e,a)&&Object.defineProperty(e,a,{enumerable:!0,get:t[a]})}}(),function(){l.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){l.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={524:0};l.O.j=function(t){return 0===e[t]};var t=function(t,a){var n,r,o=a[0],u=a[1],i=a[2],s=0;if(o.some((function(t){return 0!==e[t]}))){for(n in u)l.o(u,n)&&(l.m[n]=u[n]);if(i)var c=i(l)}for(t&&t(a);s<o.length;s++)r=o[s],l.o(e,r)&&e[r]&&e[r][0](),e[r]=0;return l.O(c)},a=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];a.forEach(t.bind(null,0)),a.push=t.bind(null,a.push.bind(a))}();var a=l.O(void 0,[504],(function(){return l(4120)}));a=l.O(a)})();
//# sourceMappingURL=app.70781fc7.js.map