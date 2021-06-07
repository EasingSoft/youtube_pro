setInterval(function() {
var ytdown_api_url="https://jfududuud.herokuapp.com/api/info?url=";
var jdjd=document.getElementById("wdymbyndown");
if(jdjd == null){
function insertAfter(referenceNode, newNode) {referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);}
var sneudiv=document.createElement("div");
sneudiv.style.height="50px";
sneudiv.style.width="100%";
sneudiv.style.display="flex";
sneudiv.setAttribute("id","wdymbyndown");
sneudiv.style.alignItems="center";
sneudiv.style.justifyContent="center";
var jdjddiv=document.getElementsByClassName('slim-video-metadata-actions')[0];
insertAfter(jdjddiv,sneudiv);
var pkcjkD=document.createElement("div");
pkcjkD.style.display="block";
pkcjkD.style.height="90%";
pkcjkD.style.width="auto";
pkcjkD.style.textAlign="center";
pkcjkD.style.marginRight="10px";
pkcjkD.style.fontSize="12px";
pkcjkD.innerHTML="<span style='font-size:20px;'>&#8681;</span><br>Download";
sneudiv.appendChild(pkcjkD);
pkcjkD.addEventListener("click",
function(){
pkcjkD.style.pointerEvents="none";
pkcjkD.innerHTML="&#9673;";
pkcjkD.style.lineHeight="40px";
var jsiu=0;
var iugv=setInterval(() => {if(jsiu == 0){pkcjkD.innerHTML="&#9675;";jsiu=1;} else{jsiu=0;pkcjkD.innerHTML="&#9673;";}},500);
fetch(ytdown_api_url+window.location.href)
.then(response => {
return response.json();
})
.then(jsonObject => {
clearInterval(iugv);
pkcjkD.style.lineHeight="normal";
pkcjkD.innerHTML="<span style='font-size:20px;'>&#8681;</span><br>Download";
pkcjkD.style.pointerEvents="auto";
Android.downvid(jsonObject.info.url);
})
.catch(error => {
clearInterval(iugv);
pkcjkD.style.lineHeight="normal";
pkcjkD.innerHTML="<span style='font-size:20px;'>&#8681;</span><br>Download";
pkcjkD.style.pointerEvents="auto";
Android.showToast("An Error Occurred");
});
});
var ipkcjkD=document.createElement("div");
ipkcjkD.style.display="block";
ipkcjkD.style.height="90%";
ipkcjkD.style.width="auto";
ipkcjkD.style.textAlign="center";
ipkcjkD.style.fontSize="12px";
ipkcjkD.style.marginLeft="10px";
ipkcjkD.innerHTML="<span style='font-size:22px;'>&#9712;</span><br>PIP Mode";
sneudiv.appendChild(ipkcjkD);
ipkcjkD.addEventListener("click",
function(){
document.getElementsByClassName('video-stream')[0].play();
Android.pipvid("pip");
});
}
},50);
