﻿/**************************************************
LEADTOOLS (C) 1991-2016 LEAD Technologies, Inc. ALL RIGHTS RESERVED.
This software is protected by United States and International copyright laws.
Any copying, duplication, deployment, redistribution, modification or other
disposition hereof is STRICTLY PROHIBITED without an express written license
granted by LEAD Technologies, Inc.. This notice may not be removed or otherwise
altered under any circumstances.
Portions of this product are licensed under US patent 5,327,254 and foreign
counterparts.
For more information, contact LEAD Technologies, Inc. at 704-332-5532 or visit
https://www.leadtools.com
**************************************************/
// Leadtools.ImageProcessing.Color.js
// Version:19.0.0.2
onmessage=function(a){Leadtools_ImageProcessing_Color_Main(a)};var _callback=null;
Leadtools_ImageProcessing_Color_Main=function(a){if(!a)throw Error("'eventIn' must not be null");var b=a;a.data?(b=a.data,_callback=postMessage):_callback=b.callback;if(!b.imageData)throw Error("'imageData' must not be null");if(!_callback)throw Error("'callback' must not be null");if(void 0==b.imageData.width||void 0==b.imageData.height)b.imageData.width=b.width,b.imageData.height=b.height;switch(b.command){case "Invert":Leadtools_ImageProcessing_Color_Invert(b);break;case "Grayscale":Leadtools_ImageProcessing_Color_Grayscale(b);
break;case "ColorCount":Leadtools_ImageProcessing_Color_ColorCount(b);break;case "BrightnessContrast":Leadtools_ImageProcessing_Color_BrightnessContrast(b);break;case "HSL":Leadtools_ImageProcessing_Color_HSL(b);break;case "StretchHistogram":Leadtools_ImageProcessing_Color_StretchHistogram(b);break;case "AdaptiveContrast":Leadtools_ImageProcessing_Color_AdaptiveContrast(b);break;case "HistogramEqualize":Leadtools_ImageProcessing_Color_HistogramEqualize(b);break;case "GammaCorrect":Leadtools_ImageProcessing_Color_GammaCorrect(b);
break;case "AutoColorLevel":Leadtools_ImageProcessing_Color_AutoColorLevel(b);break;case "ColorLevel":Leadtools_ImageProcessing_Color_ColorLevel(b);break;case "ContrastBrightnessIntensity":Leadtools_ImageProcessing_Color_ContrastBrightnessIntensity(b);break;case "ChangeHueSaturationIntensity":Leadtools_ImageProcessing_Color_ChangeHueSaturationIntensity(b);break;default:throw Error("Unrecognized image processing command: "+b.command);}};var _progressData=null;
function increaseProgress(){null==_progressData.value&&(_progressData.value=_progressData.start,_progressData.count=0);_progressData.count++;var a=_progressData.start+(_progressData.end-_progressData.start)*(_progressData.count/_progressData.resultImageHeight);return parseInt(a)>_progressData.value&&(_progressData.value=parseInt(a),a={status:"Progress",percentage:_progressData.value},_progressData.value==_progressData.end&&(_progressData.value=null),_callback(a),a.abort)?!0:!1}
Leadtools_ImageProcessing_Color_ChangeHueSaturationIntensity=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;var c=a.hue,e=a.saturation,d=a.intensity,g=null;if(null!=a.hsiData)for(var g=Array(a.hsiData.length),h=0;h<g.length;h++)g[h]={},g[h].nIntensity=a.hsiData[h].intensity,g[h].nSaturation=a.hsiData[h].saturation,g[h].nHue=a.hsiData[h].hue,g[h].nOuterLow=a.hsiData[h].outerLow,g[h].nOuterHigh=a.hsiData[h].outerHigh,
g[h].nInnerLow=a.hsiData[h].innerLow,g[h].nInnerHigh=a.hsiData[h].innerHigh;L_ChangeHueSatIntBitmap(b,c,e,d,g,null!=g?g.length:0);_callback&&_callback({status:"Completed",imageData:b})};function L_ChangeHueSatIntBitmap(a,b,c,e,d,g){var h=1;if(null==d&&0<g)throw Error("ERROR_INV_PARAMETER");h=HSI_CHECK_PARM(a,b,c,e,d,g);if(1!=h)throw Error("ERROR_INV_PARAMETER");HSIBitmap(a,b,c,e,d,g,0,100)}
function HSIBitmap(a,b,c,e,d,g){var h=null,j,l,n,o,m={};o=a.width;j=Array(361);for(n=0;n<j.length;n++)j[n]={};l=3*a.width+12;InitHSIBuffer(j,b,c,e,d,g,8);h=L_AllocateUint8Array(3*a.width+12);b=L_AllocateInt32Array(o);c=Array(l);for(n=0;n<c.length;n++)c[n]={};l=Array(l);for(n=0;n<l.length;n++)l[n]={};m.left=0;m.right=a.width;m.top=0;m.bottom=a.height;for(n=m.top;n<m.bottom;)L_ImageProcessGet(a,h,n),HSI_Line8(h,c,l,b,j,3,m),L_ImageProcessPut(a,h,n),n++}
function HSI_Line8(a,b,c,e,d,g,h){L_ProcessCSC8(1,a,c,null,h.left,h.right);GetHueScan(c,e,h);HSIChangeHue(c,e,d,h);L_ProcessCSC8(17,a,c,null,h.left,h.right);L_ProcessCSC8(2,a,b,null,h.left,h.right);HSIChangeSat(b,e,d,h);L_ProcessCSC8(18,a,b,null,h.left,h.right);HSIChangeBright(a,e,d,h,g)}
function HSIChangeBright(a,b,c,e,d){var g,h,j;h=e.right*d;g=e.left*d;for(e=e.left;g<h;g+=d,e++)j=c[b[e]].nIntensity,a[g]=parseInt(Math.max(0,Math.min(255,a[g]+j))),a[g+1]=parseInt(Math.max(0,Math.min(255,a[g+1]+j))),a[g+2]=parseInt(Math.max(0,Math.min(255,a[g+2]+j)))}function HSIChangeSat(a,b,c,e){var d,g;d=e.right;for(e=e.left;e<d;e++)g=a[e].uSat,a[e].uSat=parseInt(Math.min(100,Math.max(0,g+g*c[b[e]].nSaturation/100)))}
function HSIChangeHue(a,b,c,e){var d,g;g=e.right;for(e=e.left;e<g;e++)d=a[e].uHue+c[b[e]].nHue,0>d&&(d+=360),a[e].uHue=d%360}function GetHueScan(a,b,c){var e;e=c.right;for(c=c.left;c<e;c++)b[c]=a[c].uHue}
function L_ProcessCSC8(a,b,c,e,d,g){if(!c)throw Error("ERROR_INV_PARAMETER");if(!b)throw Error("ERROR_INV_PARAMETER");switch(a){case 1:CSC_RGBToHSV8(b,c,d,g);break;case 2:CSC_RGBToHLS8(b,c,d,g);break;case 17:CSC_HSVToRGB8(b,c,d,g);break;case 18:CSC_HLSToRGB8(b,c,d,g);break;default:throw Error("ERROR_INV_PARAMETER");}}
function CSC_HLSToRGB8(a,b,c,e){for(var d,g,h,j,l,n=3*c;c<e;c++)h=b[c].uHue,j=b[c].uLum,l=b[c].uSat,g=128>=j?parseInt(j*(100+l)/100):parseInt(j+(255-j)*l/100),d=(j<<1)-g,l?(a[n]=CSC_RGBValueFromHLS8(d,g,h-120),a[n+1]=CSC_RGBValueFromHLS8(d,g,h),a[n+2]=CSC_RGBValueFromHLS8(d,g,h+120)):a[n]=a[n+1]=a[n+2]=j,n+=3}function CSC_RGBValueFromHLS8(a,b,c){360<c?c-=360:0>c&&(c+=360);return 60>=c?parseInt(a+6*(b-a)*c/360):180>=c?b:240>=c?parseInt(a+6*(b-a)*(240-c)/360):a}
function CSC_HSVToRGB8(a,b,c,e){for(var d,g,h,j=3*c;c<e;c++)d=b[c].uHue,g=b[c].uSat,h=b[c].uVal,0==g?a[j+2]=a[j+1]=a[j]=h:(g=parseInt((100*h-h*g)/100),120>=d?(a[j]=g,60>=d?(a[j+2]=h,a[j+1]=parseInt(g+d*(h-g)/(120-d))):(a[j+1]=h,a[j+2]=parseInt(g+(120-d)*(h-g)/d))):240>=d?(a[j+2]=g,180>=d?(a[j+1]=h,a[j]=parseInt(g+(d-120)*(h-g)/(240-d))):(a[j]=h,a[j+1]=parseInt(g+(240-d)*(h-g)/(d-120)))):(a[j+1]=g,300>=d?(a[j]=h,a[j+2]=parseInt(g+(d-240)*(h-g)/(360-d))):(a[j+2]=h,a[j]=parseInt(g+(360-d)*(h-g)/(d-240))))),
j+=3}function CSC_RGBToHLS8(a,b,c,e){for(var d=3*c,g,h,j,l,n,o,m;c<e;c++)g=a[d],h=a[d+1],j=a[d+2],l=Math.max(Math.max(g,h),j),n=Math.min(Math.min(g,h),j),o=l-n,m=l+n,b[c].uLum=m>>1,b[c].uSat=l==n?0:128>=b[c].uLum?parseInt(100*o/m):parseInt(100*o/(510-m)),b[c].uSat?(g=l==j?parseInt(60*(h-g)/o):l==h?parseInt(120+60*(g-j)/o):parseInt(240+60*(j-h)/o),b[c].uHue=0>g?360+g:g):b[c].uHue=0,d+=3}
function CSC_RGBToHSV8(a,b,c,e){for(var d=3*c,g,h,j,l;c<e;c++)g=a[d+2],h=a[d+1],j=a[d],l=Math.min(Math.min(g,h),j),b[c].uVal=Math.max(Math.max(g,h),j),b[c].uVal==l?(b[c].uHue=0,b[c].uSat=0):(b[c].uHue=l==j?parseInt(0+120*(h-l)/(h+g-(l<<1))):l==g?parseInt(120+120*(j-l)/(j+h-(l<<1))):parseInt(240+120*(g-l)/(g+j-(l<<1))),b[c].uSat=parseInt(100*(b[c].uVal-l)/b[c].uVal)),d+=3}
function InitHSIBuffer(a,b,c,e,d,g){var h;for(h=0;360>=h;h++)a[h].nHue=b,a[h].nSaturation=c,a[h].nIntensity=e;for(b=0;b<g;b++)for(h=0;360>=h;h++)if(c=h<d[b].nOuterLow&&360<d[b].nOuterHigh?h+360:h,c>=d[b].nOuterLow&&c<=d[b].nOuterHigh)c>=d[b].nInnerLow&&c<=d[b].nInnerHigh?(a[h].nHue+=d[b].nHue,18E3<a[h].nHue&&(a[h].nHue=-(36E3-a[h].nHue)),-18E3>a[h].nHue&&(a[h].nHue=36E3+a[h].nHue),a[h].nSaturation+=d[b].nSaturation,a[h].nIntensity+=d[b].nIntensity):(c=c<d[b].nInnerLow?(h-d[b].nOuterLow)/(d[b].nInnerLow-
d[b].nOuterLow):Math.abs((c-d[b].nOuterHigh)/(d[b].nOuterHigh-d[b].nInnerHigh)),a[h].nHue+=parseInt(c*d[b].nHue),18E3<a[h].nHue&&(a[h].nHue=-(36E3-a[h].nHue)),-18E3>a[h].nHue&&(a[h].nHue=36E3+a[h].nHue),a[h].nSaturation+=parseInt(c*d[b].nSaturation),a[h].nIntensity+=parseInt(c*d[b].nIntensity));for(h=0;360>=h;h++)a[h].nHue=parseInt(100*a[h].nHue/1E4),a[h].nSaturation=parseInt(100*a[h].nSaturation/1E3),a[h].nIntensity=parseInt((255*Math.min(1E3,a[h].nIntensity)+500)/1E3)}
function HSI_CHECK_PARM(a,b,c,e,d,g){if(null==a)throw Error("ERROR_NO_BITMAP");if(1E3<c||-1E3>c||1E3<e||-1E3>e||18E3<b||-18E3>b)return-13;for(a=0;a<g;a++)if(1E3<d[a].nSaturation||-1E3>d[a].nSaturation||1E3<d[a].nIntensity||-1E3>d[a].nIntensity||18E3<d[a].nHue||-18E3>d[a].nHue||359<d[a].nOuterLow||-359>d[a].nOuterLow||359<d[a].nOuterHigh||-359>d[a].nOuterHigh||359<d[a].nInnerLow||-359>d[a].nInnerLow||359<d[a].nInnerHigh||-359>d[a].nInnerHigh)return-13;for(a=0;a<g;a++)if(0>d[a].nOuterLow&&(d[a].nOuterLow+=
360),0>d[a].nOuterHigh&&(d[a].nOuterHigh+=360),0>d[a].nInnerLow&&(d[a].nInnerLow+=360),0>d[a].nInnerHigh&&(d[a].nInnerHigh+=360),d[a].nOuterLow>d[a].nOuterHigh)if(0<=d[a].nOuterHigh&&(d[a].nOuterHigh+=360),0<=d[a].nInnerHigh)d[a].nInnerHigh+=360;for(a=0;a<g;a++)if(d[a].nOuterLow>d[a].nOuterHigh||d[a].nInnerLow>d[a].nInnerHigh||d[a].nInnerLow<d[a].nOuterLow||d[a].nInnerHigh>d[a].nOuterHigh)return-13;return 1}
Leadtools_ImageProcessing_Color_ContrastBrightnessIntensity=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;L_IntContBrightIntBitmap(b,a.contrast,a.brightness,a.intensity);_callback&&_callback({status:"Completed",imageData:b})};function L_IntContBrightIntBitmap(a,b,c,e){ContBrightIntBitmap(a,b,c,e)}
function ContBrightIntBitmap(a,b,c,e){var d,g,h;if(-1E3>c||1E3<c)throw Error("ERROR_INV_PARAMETER");if(-1E3>b||1E3<b)throw Error("ERROR_INV_PARAMETER");if(-1E3>e||1E3<e)throw Error("ERROR_INV_PARAMETER");if(c||b||e){d=L_AllocateUint32Array(256);if(b)g=Math.round(128*Math.abs(b)/4E3),h=256-parseInt(f(128*Math.abs(b)/1E3)),h<g&&(h=g),g*=1,h*=1,0<b?(RemapInterval(0,g,0,0,d),RemapInterval(g,h,0,256,d),RemapInterval(h,256,255,255,d)):RemapInterval(0,256,g,h,d);else for(b=0;256>b;b++)d[b]=b;c=parseInt((255*
c+500)/1E3);g=255;e&&(g=512*((e+1E3)/2E3));for(b=0;256>b;b++)e=parseInt(g/255*d[b])+c,d[b]=Math.max(0,Math.min(255,e));L_IntRemapBitmapIntensity(a,d,256,0,0,100)}}function RemapInterval(a,b,c,e,d){var g;for(g=a;g<b;g++)d[g]=c+Math.max(0,Math.min(e,Math.round((g-a)*(e-c)/(b-a))))}function f(a){return 5.34*Math.pow(1.5,Math.log(a)/Math.log(2))}
Leadtools_ImageProcessing_Color_ColorLevel=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;var c={},e=a.flag;if(null==a.redChannelColorLevelData&&e&1)throw Error("ERROR INV PARAMETER");e&1&&(c.red={},c.red.nMinInput=a.redChannelColorLevelData.minInput,c.red.nMaxInput=a.redChannelColorLevelData.maxInput,c.red.nMinOutput=a.redChannelColorLevelData.minOutput,c.red.nMaxOutput=a.redChannelColorLevelData.maxOutput,
c.red.uGamma=a.redChannelColorLevelData.gamma);if(null==a.greenChannelColorLevelData&&e&16)throw Error("ERROR INV PARAMETER");e&16&&(c.green={},c.green.nMinInput=a.greenChannelColorLevelData.minInput,c.green.nMaxInput=a.greenChannelColorLevelData.maxInput,c.green.nMinOutput=a.greenChannelColorLevelData.minOutput,c.green.nMaxOutput=a.greenChannelColorLevelData.maxOutput,c.green.uGamma=a.greenChannelColorLevelData.gamma);if(null==a.blueChannelColorLevelData&&e&256)throw Error("ERROR INV PARAMETER");
e&256&&(c.blue={},c.blue.nMinInput=a.blueChannelColorLevelData.minInput,c.blue.nMaxInput=a.blueChannelColorLevelData.maxInput,c.blue.nMinOutput=a.blueChannelColorLevelData.minOutput,c.blue.nMaxOutput=a.blueChannelColorLevelData.maxOutput,c.blue.uGamma=a.blueChannelColorLevelData.gamma);if(null==a.masterChannelColorLevelData&&e&4096)throw Error("ERROR INV PARAMETER");e&4096&&(c.master={},c.master.nMinInput=a.masterChannelColorLevelData.minInput,c.master.nMaxInput=a.masterChannelColorLevelData.maxInput,
c.master.nMinOutput=a.masterChannelColorLevelData.minOutput,c.master.nMaxOutput=a.masterChannelColorLevelData.maxOutput,c.master.uGamma=a.masterChannelColorLevelData.gamma);ColorLevelBitmap(b,c,e,0,100);_callback&&_callback({status:"Completed",imageData:b})};
Leadtools_ImageProcessing_Color_AutoColorLevel=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;var c=50,e=50;null!=a.blackClip&&(c=a.blackClip);null!=a.whiteClip&&(e=a.whiteClip);var d={blue:{},green:{},red:{},master:{}};L_IntAutoColorLevelBitmap(b,d,c,e,a.flag|a.type);var a={},c={},e={},g={};a.minInput=d.master.nMinInput;a.maxInput=d.master.nMaxInput;a.minOutput=d.master.nMinOutput;a.maxOutput=d.master.nMaxOutput;
a.gamma=d.master.uGamma;c.minInput=d.red.nMinInput;c.maxInput=d.red.nMaxInput;c.minOutput=d.red.nMinOutput;c.maxOutput=d.red.nMaxOutput;c.gamma=d.red.uGamma;e.minInput=d.green.nMinInput;e.maxInput=d.green.nMaxInput;e.minOutput=d.green.nMinOutput;e.maxOutput=d.green.nMaxOutput;e.gamma=d.green.uGamma;g.minInput=d.blue.nMinInput;g.maxInput=d.blue.nMaxInput;g.minOutput=d.blue.nMinOutput;g.maxOutput=d.blue.nMaxOutput;g.gamma=d.blue.uGamma;_callback&&_callback({status:"Completed",imageData:b,master:a,red:c,
green:e,blue:g})};function L_IntAutoColorLevelBitmap(a,b,c,e,d){var g;g=!(d&4);if(!g&&!b)throw Error("ERROR INV PARAMETER");d&=3;if(!d)throw Error("ERROR INV PARAMETER");UtilAutoLevel_Contrast(a,b,c,e,1==d,3==d,g,0,100)}
function UtilAutoLevel_Contrast(a,b,c,e,d,g,h,j,l){var n=null,o,m,k=Array(4);k[0]={};k[1]={};k[2]={};k[3]={};var r=255,s,t,u,p,q,v,y,z,w,A;v=r+1;k[0].pValue={};k[1].pValue={};k[2].pValue={};k[3].pValue={};if(1E4<c||0>c)throw Error("ERROR INV PARAMETER");if(1E4<e||0>e)throw Error("ERROR INV PARAMETER");if(e==c&&0==e)null!=b&&(L_InitLevelExtremesBitmap(a,b.red),L_InitLevelExtremesBitmap(a,b.green),L_InitLevelExtremesBitmap(a,b.blue),L_InitLevelExtremesBitmap(a,b.master));else{s=parseInt(e/1E4*a.width*
a.height+0.5);t=parseInt(c/1E4*a.width*a.height+0.5);r++;n=Array(v);L_MEMSET(n,0,v);for(p=0;3>=p;p++)L_InitLevelExtremesBitmap(a,k[p].pValue);d?(p=0,q=3):(p=3,q=4);A=parseInt((h?40*(l-j)/100:l-j)/(d?3:1));for(j-=1;p<q;p++){o=m=0;y=z=!0;w=j+1;j=Math.min(w+A,l);if(!d&&g){u=null;var x,B,D;s=parseInt(e/1E4*3*a.width*a.height+0.5);t=parseInt(c/1E4*3*a.width*a.height+0.5);u=Array(v);D=(j-w)/3;B=w;for(w=0;2>=w;w++){x=B;B=x+D;2==w&&(B=j);L_IntGetBitmapHistogram(a,u,v,w+1,x,B);for(x=0;x<v;x++)n[x]+=u[x]}delete u}else L_IntGetBitmapHistogram(a,
n,v,3-p,w,j);for(u=0;u<v;u++)if(o+=n[u],m+=n[v-u-1],y&&o>t&&(y=!1,k[p].pValue.nMinInput=u),z&&m>s&&(z=!1,k[p].pValue.nMaxInput=v-u-1),!z&&!y){k[p].pValuenMaxInput<k[p].pValue.nMinInput&&(o=k[p].pValue.nMaxInput+k[p].pValue.nMinInput>>1,k[p].pValue.nMaxInput=o,k[p].pValue.nMinInput=o);m=k[p].pValue.nMaxInput-k[p].pValue.nMinInput;if(16<=m)break;m=16-m;o=parseInt(m/2+0.5);m/=2;k[p].pValue.nMinInput<o&&(m+=o-k[p].pValue.nMinInput,o=k[p].pValue.nMinInput);k[p].pValue.nMaxInput>r-1-m&&(o+=m-(r-1-k[p].pValue.nMaxInput),
m=r-1-k[p].pValue.nMaxInput);k[p].pValue.nMinInput-=o;k[p].pValue.nMaxInput+=m;break}}null!=b&&(b.red.nMinInput=k[2].pValue.nMinInput,b.red.nMaxInput=k[2].pValue.nMaxInput,b.red.nMinOutput=k[2].pValue.nMinOutput,b.red.nMaxOutput=k[2].pValue.nMaxOutput,b.red.uGamma=k[2].pValue.uGamma,b.green.nMinInput=k[1].pValue.nMinInput,b.green.nMaxInput=k[1].pValue.nMaxInput,b.green.nMinOutput=k[1].pValue.nMinOutput,b.green.nMaxOutput=k[1].pValue.nMaxOutput,b.green.uGamma=k[1].pValue.uGamma,b.blue.nMinInput=k[0].pValue.nMinInput,
b.blue.nMaxInput=k[0].pValue.nMaxInput,b.blue.nMinOutput=k[0].pValue.nMinOutput,b.blue.nMaxOutput=k[0].pValue.nMaxOutput,b.blue.uGamma=k[0].pValue.uGamma,b.master.nMinInput=k[3].pValue.nMinInput,b.master.nMaxInput=k[3].pValue.nMaxInput,b.master.nMinOutput=k[3].pValue.nMinOutput,b.master.nMaxOutput=k[3].pValue.nMaxOutput,b.master.uGamma=k[3].pValue.uGamma);delete n;h&&(b={},b.red=L_LVLCLRINFCPY(k[2].pValue),b.green=L_LVLCLRINFCPY(k[1].pValue),b.blue=L_LVLCLRINFCPY(k[0].pValue),b.master=L_LVLCLRINFCPY(k[3].pValue),
ColorLevelBitmap(a,b,4369,Math.min(j+1,l),l))}}
function ColorLevelBitmap(a,b,c,e,d){var g=Array(4),h=Array(4);h[0]={};h[1]={};h[2]={};h[3]={};var j=null,l={};if(!b)throw Error("ERROR INV PARAMETER");if(!(4369&c))throw Error("ERROR INV PARAMETER");L_MEMSET(g,null,4);h[0].pValue=c&256?b.blue:null;h[1].pValue=c&16?b.green:null;h[2].pValue=c&1?b.red:null;h[3].pValue=c&4096?b.master:null;for(c=3;0<=c;c--)if(1!=UtilCheckParam(h,c,255))throw Error("ERROR INV PARAMETER");if(h[2].pValue||h[1].pValue||h[0].pValue||h[3].pValue){j=L_AllocateUint8ArrayInit(3*
a.width+12);for(c=0;3>c;c++)if(h[3].pValue||h[c].pValue){g[c]=L_AllocateUint8ArrayInit(256);for(b=0;255>=b;b++)g[c][b]=b}UtilInitPixelValues(g,h,255,8,a);null!=_progressData&&(_progressData.start=e,_progressData.end=d);l.uStrtRowNum=0;l.uEndRowNum=a.height;l.uStrtColNum=0;l.uEndColNum=a.width;for(e=l.uStrtRowNum;e<l.uEndRowNum;e++)L_ImageProcessGet(a,j,e),UtilMapPixels8(g,j,l,3),L_ImageProcessPut(a,j,e)}}
function UtilMapPixels8(a,b,c,e){var d,g,h=c.uStrtColNum*e;for(d=c.uStrtColNum;d<c.uEndColNum;d++)for(g=0;g<e;g++)null!=a[g]&&(b[h]=a[g][b[h]]),h++}
function UtilInitPixelValues(a,b,c,e){var d=0,g=0,h=0,j=0,l=0,n=0,o=0,m=0,k,r=0,s=0,t=0,u=0,p,q,v=!1,y=!1,z=!1,w=!1;null!=b[3].pValue&&((y=b[3].pValue.nMinOutput<b[3].pValue.nMaxOutput?!1:!0,g=1/(b[3].pValue.nMaxInput-b[3].pValue.nMinInput),y?(j=b[3].pValue.nMinOutput-b[3].pValue.nMaxOutput,m=b[3].pValue.nMinOutput+b[3].pValue.nMaxOutput,u=b[3].pValue.nMinOutput,s=b[3].pValue.nMaxOutput):(j=b[3].pValue.nMaxOutput-b[3].pValue.nMinOutput,u=b[3].pValue.nMaxOutput,s=b[3].pValue.nMinOutput),100!=b[3].pValue.uGamma)?
(n=100/b[3].pValue.uGamma,z=!0):z=!1);for(k=0;3>k;k++)if(8!=e||a[k]){null!=b[k].pValue&&((v=b[k].pValue.nMinOutput<b[k].pValue.nMaxOutput?!1:!0,d=1/(b[k].pValue.nMaxInput-b[k].pValue.nMinInput),v?(h=b[k].pValue.nMinOutput-b[k].pValue.nMaxOutput,o=b[k].pValue.nMinOutput+b[k].pValue.nMaxOutput,t=b[k].pValue.nMinOutput,r=b[k].pValue.nMaxOutput):(h=b[k].pValue.nMaxOutput-b[k].pValue.nMinOutput,t=b[k].pValue.nMaxOutput,r=b[k].pValue.nMinOutput),100!=b[k].pValue.uGamma)?(l=100/b[k].pValue.uGamma,w=!0):
w=!1);for(p=0;p<=c;p++)8==e&&(q=a[k][p]),null!=b[k].pValue&&(q=q<=b[k].pValue.nMinInput?r:q>=b[k].pValue.nMaxInput?t:parseInt(d*(q-b[k].pValue.nMinInput)*h+r+0.5),v&&(q=Math.min(c,Math.max(0,o-q))),w&&(q=parseInt(Math.pow(q/c,l)*c+0.5))),null!=b[3].pValue&&(q=q<=b[3].pValue.nMinInput?s:q>=b[3].pValue.nMaxInput?u:parseInt(g*(q-b[3].pValue.nMinInput)*j+s+0.5),y&&(q=Math.min(c,Math.max(0,m-q))),z&&(q=parseInt(Math.pow(q/c,n)*c+0.5))),8==e&&(a[k][p]=Math.max(0,Math.min(q,c)))}}
function UtilCheckParam(a,b,c){return null==a[b].pValue?1:0==a[b].pValue.nMinInput&&0==a[b].pValue.nMinOutput&&a[b].pValue.nMaxInput==c&&a[b].pValue.nMaxOutput==c&&100==a[b].pValue.uGamma?(a[b].pValue=null,1):0>a[b].pValue.nMinInput||2>a[b].pValue.nMaxInput-a[b].pValue.nMinInput||c<a[b].pValue.nMaxInput||0>a[b].pValue.nMinOutput||a[b].pValue.nMinOutput>c||0>a[b].pValue.nMaxOutput||a[b].pValue.nMaxOutput>c||0==a[b].pValue.uGamma?-13:1}
function GetBitmapHistogram(a,b,c){var e,d,g,h,j,l,n;if(null==b)throw Error("ERROR INV PARAMETER");if(3<c)throw Error("ERROR INV PARAMETER");d=a.width;l=a.height;e=parseInt((24*(d-0)+7)/8);d=Array(2);d[0]=0;d[1]=a.width;j=L_AllocateUint8ArrayInit(e);L_MEMSET(b,0,256);for(h=0;h<l;h++){L_ImageProcessGet(a,j,h);for(n=0;2>n;n+=2)if(e=3*d[n],0==c)for(g=d[n];g<d[n+1];g++)b[calculateGrayValue(j[e+2],j[e+1],j[e])]++,e+=3;else{switch(c){case 1:e+=2;break;case 2:e++}for(g=d[n];g<d[n+1];g++)b[j[e]]++,e+=3}}}
function L_IntGetBitmapHistogram(a,b,c,e,d,g){if(e&240&&16!=(e&240)&&e&240)throw Error("ERROR_INV_PARAMETER");switch(e&15){case 3:case 2:case 1:case 0:break;default:throw Error("ERROR_INV_PARAMETER");}GetBitmapHistogram(a,b,e&15,d,g)}function L_InitLevelExtremesBitmap(a,b){if(!b)throw Error("ERROR_INV_PARAMETER");b.nMinInput=0;b.nMaxInput=255;b.nMinOutput=0;b.nMaxOutput=255;b.uGamma=100}
function L_LVLCLRINFCPY(a){var b={};b.nMinInput=a.nMinInput;b.nMaxInput=a.nMaxInput;b.nMinOutput=a.nMinOutput;b.nMaxOutput=a.nMaxOutput;b.uGamma=a.uGamma;return b}function L_MEMSET(a,b,c){for(var e=0;e<c;e++)a[e]=b}
Leadtools_ImageProcessing_Color_GammaCorrect=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;var c=a.gamma,e=256,d;if(!c)throw Error("ERROR INV PARAMETER");a=L_AllocateUint32Array(e);d=parseFloat(100/c);e--;for(c=0;c<=e;c++)a[c]=parseInt(Math.pow(c/e,d)*e+0.5);L_IntRemapBitmapIntensity(b,a,e+1,256,0,100);_callback&&_callback({status:"Completed",imageData:b})};
function L_IntRemapBitmapIntensity(a,b,c,e,d,g){for(var h=null,h=L_AllocateUint32Array(c),j=0;j<c;j++)h[j]=b[j];IntRemapBitmapIntensity(a,h,c,e,d,g)}function IntRemapBitmapIntensity(a,b,c,e){e&256&&(e&16||CheckAndUpdateLUTData(a,b,c),RempBitmapNormal(a,b,c,b,c));e&256||CheckAndUpdateLUTData(a,b,c);RemapBitmapIntensity(a,b,e&15);e&256&&delete b}function RempBitmapNormal(a,b,c,e){e=L_AllocateUint32ArrayInit(c);for(a=0;a<c;a++)e[a]=b[a]}
function CheckAndUpdateLUTData(a,b,c){for(var e,a=0;a<c;a++){e=b[a];0>e&&(e+=c);if(0>e||e>=c)throw Error("ERROR INV PARAMETER");b[a]=e}}
Leadtools_ImageProcessing_Color_HistogramEqualize=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;switch(a.type){case 4:L_GrayHistoEqualizeBitmap(b);break;case 1:L_RGBHistogramEqu8(b);break;case 2:L_YUVHistogramEqu8(b);break;default:throw Error("ERROR INV PARAMETER");}_callback&&_callback({status:"Completed",imageData:b})};
function L_GrayHistoEqualizeBitmap(a){var b,c,e,d;b=L_AllocateUint32Array(256);c=GetHistogram(a.data);for(e=1;256>e;e++)c[e]+=c[e-1];d=a.width*a.height;if(16777215<d)for(e=0;256>e;e++)b[e]=parseInt(c[e]/(d/255));else for(e=0;256>e;e++)b[e]=parseInt(255*c[e]/d);RemapBitmapIntensity(a,b,0)}function RemapBitmapIntensity(a,b,c){if(3<c)throw Error("ERROR_INV_PARAMETER");if(null==b)throw Error("ERROR_INV_PARAMETER");RemapBitmapIntensity_Pixel(a,b,c)}
function RemapBitmapIntensity_Pixel(a,b,c){var e=null,d=null,g,h,j,l,n;zScanSize=3*a.width+12;e=L_AllocateUint8Array(zScanSize);d=Array(2);d[0]=0;l=d[1]=a.width;n=a.height;for(h=0;h<n;h++){L_ImageProcessGet(a,e,h);for(j=0;2>j;j+=2)if(0==c)for(i=0;i<3*l;i+=3)e[i]=Math.min(b[e[i]],255),e[i+1]=Math.min(b[e[i+1]],255),e[i+2]=Math.min(b[e[i+2]],255);else for(g=d[j];g<3*d[j+1];g+=3){if(0==c||1==c)e[g]=Math.min(b[e[g]],255);if(0==c||2==c)e[g+1]=Math.min(b[e[g+1]],255);if(0==c||3==c)e[g+2]=Math.min(b[e[g+
2]],255)}L_ImageProcessPut(a,e,h)}}
function L_YUVHistogramEqu8(a){var b,c,e=0,d;b=L_AllocateInt32ArrayInit(256);var g=L_AllocateUint32ArrayInit(256),h,j=null,l,n;c=a.width*a.height;l=a.width;n=a.height;j=L_AllocateUint8Array(3*a.width+12);h=L_AllocateUint32Array(c);RGBtoYUV8(a,h);GetYUVHistogram8(h,b,c);GetYUVHistCumalate8(b,g,c);for(c=0;c<n;c++){L_ImageProcessGet(a,j,c);for(b=0;b<3*l;b+=3)d=g[h[e]]-h[e],j[b]=Math.min(255,Math.max(0,j[b]+d)),j[b+1]=Math.min(255,Math.max(0,j[b+1]+d)),j[b+2]=Math.min(255,Math.max(0,j[b+2]+d)),e++;L_ImageProcessPut(a,
j,c)}}function GetYUVHistogram8(a,b,c){for(var e=0,e=0;e<c;e++)b[a[e]]++}function GetYUVHistCumalate8(a,b,c){var e,d;for(e=d=0;256>e;e++)d+=a[e],b[e]=parseInt(255*(d/c))}function RGBtoYUV8(a,b){var c=0,e=0,d=0,g=null,h,j;b[0]=null;g=L_AllocateUint8Array(3*a.width+12);h=a.width;j=a.height;for(e=d=0;e<j;e++){L_ImageProcessGet(a,g,e);for(c=0;c<3*h;c+=3)b[d]=Math.min(255,9798*g[c+2]+19235*g[c+1]+3736*g[c]>>15),d++}}
function L_RGBHistogramEqu8(a){var b,c;b=L_AllocateUint32ArrayInit(256);c=L_AllocateUint32ArrayInit(256);var e=L_AllocateUint32ArrayInit(256),d=L_AllocateUint32ArrayInit(256),g=L_AllocateUint32ArrayInit(256),h=L_AllocateUint32ArrayInit(256),j,l,n,o;o=a.width*a.height;l=a.width;n=a.height;j=L_AllocateUint8Array(3*a.width+12);GetRGBHistogram8(a,b,c,e);GetRGBHistCumalate8(b,d,o);GetRGBHistCumalate8(c,g,o);GetRGBHistCumalate8(e,h,o);for(c=0;c<n;c++){L_ImageProcessGet(a,j,c);for(b=0;b<3*l;b+=3)j[b]=Math.min(h[j[b]],
255),j[b+1]=Math.min(g[j[b+1]],255),j[b+2]=Math.min(d[j[b+2]],255);L_ImageProcessPut(a,j,c)}}function GetRGBHistCumalate8(a,b,c){var e,d;for(e=d=0;256>e;e++)d+=a[e],b[e]=parseInt(255*(d/c))}function GetRGBHistogram8(a,b,c,e){var d,g,h,j,l=null;h=a.width;j=a.height;l=L_AllocateUint8Array(3*a.width+12);for(g=0;g<j;g++){L_ImageProcessGet(a,l,g);for(d=0;d<3*h;d+=3)b[l[d+2]]++,c[l[d+1]]++,e[l[d]]++}}
Leadtools_ImageProcessing_Color_AdaptiveContrast=function(a){var b=a.imageData;a.useProgress?(_progressData={start:0,end:100},_progressData.resultImageHeight=b.height):_progressData=null;var c=a.dimension,e=a.amount,d=a.type,g,h,j,l,n,o,m,k,r,s,a=!1;if(!c)throw Error("ERROR INV PARAMETER");1==c&&_callback&&_callback({status:"Completed",imageData:b});if(100>e)throw Error("ERROR INV PARAMETER");if(1==d)a=!0;else if(2==d)a=!1;else throw Error("ERROR INV PARAMETER");r=c-1>>1;o=c*c;d=c-1>>1;n=(c>>1)+1;
s=3*b.width+12;var t=L_AllocateUint8Array(s),u=L_AllocateUint8Array(s),p=L_AllocateUint8Array(s);s=L_AllocateUint8Array(s);var q=Array(c),v=Array(c),y=L_AllocateUint32Array(256);h=3*b.width;for(g=0;g<c;g++)v[g]=L_AllocateUint8Array(h);k=b.height-1;l=-d;j=-d;m=0>l?0:l;for(g=h=0;h<b.height;)L_ImageProcessGet(b,v[g],m),g++,g==c&&(g=0),j++,0<=l&&l<k&&m++,l++,j>=n&&(Dry_AsendScans(v,q,c,r),ACELineMaxMin8(b,q,t,y,d,n,c,o,3,u,p,e,a,s),L_ImageProcessPut(b,t,h),h++,r++,r==c&&(r=0));_callback&&_callback({status:"Completed",
imageData:b})};function Dry_AsendScans(a,b,c,e){var d,g,h=parseInt(c/2);b[h]=a[e];d=h-1;for(g=e-1;0<=d;d--,g--)b[d]=0>g?a[g+c]:a[g];d=h+1;for(g=e+1;d<c;d++,g++)b[d]=g>=c?a[g-c]:a[g]}
function ACELineMaxMin8(a,b,c,e,d,g,h,j,l,n,o,m,k,r){var s,t,u,p,q,v,y,z,w,A,x,B,D,G,C=L_AllocateUint32ArrayInit(1),F,H=!1;D=(g-(h+1)%2)*l;G=(d+(h+1)%2)*l;for(q=0;q<l;q++){C[0]=0;F=!1;ACEGetHistogram8(a,b,q,e,d,g,h,q,l,k,C);v=GetMax8(e);z=GetMin8(e);o[q]=v;n[q]=z;k&&(r[q]=parseInt(C[0]/j));t=a.width*l-(l-q);u=a.width*l-(l-q);var E=1;for(p=q+l;p<=t;p+=l){A=p-D;A<q&&(A=q);x=p+G;x>u&&(x=u);w=z;y=v;for(B=0;B<h;B++)s=b[B],e[s[A]]--,k&&(C[0]-=s[A]),s[A]==v&&(F=!0),s[A]==z&&(H=!0),e[s[x]]++,k&&(C[0]+=s[x]),
s[x]<w&&(w=s[x]),s[x]>y&&(y=s[x]);v=F&&y==v?GetMaxFast8(e,v):y;z=GetMinFast8(e,z,w,H);o[q+E*l]=v;n[q+E*l]=z;k&&(r[q+E*l]=parseInt(C[0]/j));E++}}if(k){k=100/m;for(d=0;d<a.width*l;d+=l)j=o[d],g=n[d],m=r[d],e=b[parseInt(h/2)][d],j=Math.max(j-m,m-g),g=e-m,c[d]=j?parseInt(Math.min(255,Math.max(0,j*ACE_SIGN(g)*Math.pow(Math.abs(g)/j,k)+m))):e,j=o[d+1],g=n[d+1],m=r[d+1],e=b[parseInt(h/2)][d+1],j=Math.max(Math.abs(j-m),Math.abs(m-g)),g=e-m,c[d+1]=j?parseInt(Math.min(255,Math.max(0,j*ACE_SIGN(g)*Math.pow(Math.abs(g)/
j,k)+m))):e,j=o[d+2],g=n[d+2],m=r[d+2],e=b[parseInt(h/2)][d+2],j=Math.max(Math.abs(j-m),Math.abs(m-g)),g=e-m,c[d+2]=j?parseInt(Math.min(255,Math.max(0,j*ACE_SIGN(g)*Math.pow(Math.abs(g)/j,k)+m))):e}else for(d=0;d<a.width*l;d+=l)j=o[d],g=n[d],e=b[parseInt(h/2)][d],j-=g,r=parseInt(j*m/100),r=Math.min(255,r),c[d]=j?m?parseInt(Math.max(0,Math.min(255,(e-g)*r/j+g-(r-j)/2))):parseInt(255*(e-g)/j):e,j=o[d+1],g=n[d+1],e=b[parseInt(h/2)][d+1],j-=g,r=parseInt(j*m/100),r=Math.min(255,r),c[d+1]=j?m?parseInt(Math.max(0,
Math.min(255,(e-g)*r/j+g-(r-j)/2))):parseInt(255*(e-g)/j):e,j=o[d+2],g=n[d+2],e=b[parseInt(h/2)][d+2],j-=g,r=parseInt(j*m/100),r=Math.min(255,r),c[d+2]=j?m?parseInt(Math.max(0,Math.min(255,(e-g)*r/j+g-(r-j)/2))):parseInt(255*(e-g)/j):e}function ACEGetHistogram8(a,b,c,e,d,g,h,j,l,n,o){var m,k,r;for(m=0;256>m;m++)e[m]=0;g=c+g*l;r=a.width*l-(l-j);for(a=m=0;m<h;m++)for(k=c-d*l;k<g;k+=l,a++)k<=j?(e[b[m][j]]++,n&&(o[0]+=b[m][j])):k>r?(e[b[m][r]]++,n&&(o[0]+=b[m][r])):(e[b[m][k]]++,n&&(o[0]+=b[m][k]))}
function ACE_SIGN(a){return 0>a?-1:1}function GetMax8(a){for(var b=255;0>=a[b];)b--;return b}function GetMin8(a){for(var b=0;0>=a[b];)b++;return b}function GetMaxFast8(a,b){for(var c=b;0>=a[c];)c--;return c}function GetMinFast8(a,b,c,e){var d=b;if(e&&c==b){for(;0>=a[d];)d++;return d}return c}function L_ImageProcessGet(a,b,c){for(var e=0,d=4*a.width*c;d<4*a.width*(c+1);d+=4)b[e]=a.data[d+2],b[e+1]=a.data[d+1],b[e+2]=a.data[d],e+=3}
function L_ImageProcessPut(a,b,c){for(var e=0,d=4*a.width*c;d<4*a.width*(c+1);d+=4)a.data[d+2]=b[e],a.data[d+1]=b[e+1],a.data[d]=b[e+2],e+=3;null!=_progressData&&increaseProgress()}
Leadtools_ImageProcessing_Color_StretchHistogram=function(a){var b=a.imageData,c=a.useProgress,e=a.low,d=a.high,a=b.data,g=a.length;if(void 0==e||void 0==d)var h=0,j=!1;for(var l=GetHistogram(a),e=StretchHistogramData(l,e,d),d=0;d<g&&!j;d+=4)a[d]=e[a[d]],a[d+1]=e[a[d+1]],a[d+2]=e[a[d+2]],a[d+3]=255,c&&(l=Math.round(100*d/g),l>h&&(h=l,l={status:"Progress",percentage:l},_callback(l),l.abort&&(j=!0)));_callback&&!j&&_callback({status:"Completed",imageData:b})};
Leadtools_ImageProcessing_Color_Invert=function(a){for(var b=a.imageData,a=a.useProgress,c=b.data,e=c.length,d=0,g=!1,h=0;h<e&&!g;h+=4)if(c[h]=255-c[h],c[h+1]=255-c[h+1],c[h+2]=255-c[h+2],a){var j=Math.round(100*h/e);j>d&&(d=j,j={status:"Progress",percentage:j},_callback(j),j.abort&&(g=!0))}_callback&&!g&&_callback({status:"Completed",imageData:b})};
Leadtools_ImageProcessing_Color_BrightnessContrast=function(a){for(var b=a.imageData,c=a.useProgress,e=a.contrast,d=b.data,g=d.length,h=0,j=!1,a=1+Math.min(150,Math.max(-150,a.brightness))/150,e=Math.max(0,e+1),a=a*e,e=-128*e+128,l,n,o,m=0;m<g&&!j;)if(d[m]=255<(l=parseInt(d[m]*a+e))?255:0>l?0:l,m++,d[m]=255<(n=parseInt(d[m]*a+e))?255:0>n?0:n,m++,d[m]=255<(o=parseInt(d[m]*a+e))?255:0>o?0:o,m++,d[m]=255,m++,c){var k=Math.round(100*m/g);k>h&&(h=k,k={status:"Progress",percentage:k},_callback(k),k.abort&&
(j=!0))}_callback&&!j&&_callback({status:"Completed",imageData:b})};
Leadtools_ImageProcessing_Color_HSL=function(a){var b=a.imageData,c=a.useProgress,e=a.hue,d=b.data,g=d.length,h=0,j=!1,l=a.saturation/100,a=a.lightness/100,n;n=0>l?1+l:1+2*l;for(var e=e%360/360,o=0;o<g&&!j;){var m=d[o],k=d[o+1],r=d[o+2];if(e||l){var s,t,u,p,q;s=Math.max(m,k,r);t=Math.min(m,k,r);u=s-t;q=(t+s)/510;0<q&&0<u&&((0.5>=q?(p=u/(s+t)*n,1<p&&(p=1),p=q*(1+p)):(p=u/(510-s-t)*n,1<p&&(p=1),p=q+p-q*p),s=m===s?k===t?5+(s-r)/u+6*e:1-(s-k)/u+6*e:k===s?r===t?1+(s-m)/u+6*e:3-(s-r)/u+6*e:m===t?3+(s-k)/
u+6*e:5-(s-m)/u+6*e,0>s&&(s+=6),6<=s&&(s-=6),q=q+q-p,t=parseInt(s)>>0)?1===t?(m=255*(p-(p-q)*(s-t)),k=255*p,r=255*q):2===t?(m=255*q,k=255*p,r=255*(q+(p-q)*(s-t))):3===t?(m=255*q,k=255*(p-(p-q)*(s-t)),r=255*p):4===t?(m=255*(q+(p-q)*(s-t)),k=255*q,r=255*p):5===t&&(m=255*p,k=255*q,r=255*(p-(p-q)*(s-t))):(m=255*p,k=255*(q+(p-q)*(s-t)),r=255*q))}0>a?(m*=1+a,k*=1+a,r*=1+a):0<a&&(m=m*(1-a)+255*a,k=k*(1-a)+255*a,r=r*(1-a)+255*a);d[o]=0>m?0:255<m?255:m;o++;d[o]=0>k?0:255<k?255:k;o++;d[o]=0>r?0:255<r?255:r;
o++;d[o]=255;o++;c&&(m=Math.round(100*o/g),m>h&&(h=m,m={status:"Progress",percentage:m},_callback(m),m.abort&&(j=!0)))}_callback&&!j&&_callback({status:"Completed",imageData:b})};
Leadtools_ImageProcessing_Color_Grayscale=function(a){for(var b=a.imageData,a=a.useProgress,c=b.data,e=c.length,d=0,g=!1,h=0;h<e&&!g;h+=4)if(c[h]=c[h+1]=c[h+2]=0.2126*c[h]+0.7152*c[h+1]+0.0722*c[h+2],a){var j=Math.round(100*h/e);j>d&&(d=j,j={status:"Progress",percentage:j},_callback(j),j.abort&&(g=!0))}_callback&&!g&&_callback({status:"Completed",imageData:b})};
Leadtools_ImageProcessing_Color_ColorCount=function(a){for(var b=a.imageData,a=a.useProgress,c=b.data,e=c.length,d=Array(2097152),g=0;2097152>g;g++)d[g]=0;for(var h=0,j=0,l=!1,g=0;g<e&&!l;g+=4){var n=c[g]|c[g+1]<<8|c[g+2]<<16,o=n>>>3,n=n&7;d[o]>>>7-n&1||(n=1<<7-n,d[o]=d[o]&~n|n,h++);a&&(o=Math.round(100*g/e),o>j&&(j=o,o={status:"Progress",percentage:o},_callback(o),o.abort&&(l=!0)))}_callback&&!l&&_callback({status:"Completed",imageData:b,colorCount:h})};
function L_AllocateUint32Array(a){return array=void 0!=self.Uint32Array?new Uint32Array(a):Array(a)}function L_AllocateUint32ArrayInit(a){if(void 0!=self.Uint32Array)array=new Uint32Array(a);else{array=Array(a);for(var b=0;b<a;b++)array[b]=0}return array}function L_AllocateInt32Array(a){return array=void 0!=self.Int32Array?new Int32Array(a):Array(a)}
function L_AllocateInt32ArrayInit(a){if(void 0!=self.Int32Array)array=new Int32Array(a);else{array=Array(a);for(var b=0;b<a;b++)array[b]=0}return array}function L_AllocateUint8Array(a){return array=void 0!=self.Uint8Array?new Uint8Array(a):Array(a)}function L_AllocateUint8ArrayInit(a){if(void 0!=self.Uint8Array)array=new Uint8Array(a);else{array=Array(a);for(var b=0;b<a;b++)array[b]=0}return array}function calculateGrayValue(a,b,c){return(a<<1)+5*b+c+4>>3}
function GetHistogram(a){for(var b=a.length,c=0,e=L_AllocateUint32ArrayInit(256),c=0;c<b;c+=4)e[(a[c]<<1)+5*a[c+1]+a[c+2]+4>>3]++;return e}function GetRedHistogram(a){for(var b=a.length,c=0,e=L_AllocateUint32Array(b),c=0;c<b;c+=4)++e[a[c]];return e}function GetGreenHistogram(a){for(var b=a.length,c=0,e=L_AllocateUint32Array(b),c=1;c<b;c+=4)++e[a[c]];return e}function GetBlueHistogram(a){for(var b=a.length,c=0,e=L_AllocateUint32Array(b),c=2;c<b;c+=4)++e[a[c]];return e}
function StretchHistogramData(a,b,c){var e=a.length;if(void 0==b||void 0==c){for(b=0;b<e&&0==a[b];b++);for(c=e-1;0<c&&0==a[c];c--);}if(c<=b)return null;for(var a=L_AllocateUint32ArrayInit(a.length),c=c-b,d=0,g=e-1,h=0,d=0;d<e;d++)h=(d-b)*g/c+0.5>>0,a[d]=Math.max(0,Math.min(255,h));return a};
