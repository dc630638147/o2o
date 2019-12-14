$(function () {

})

//跳转到商铺编辑页面
function shopEdit(shopId) {
    window.location.href = "/shopApi/shopEditPage?shopId=" + shopId;
}
//跳转到商铺分类页面
function shopCategory(shopId) {
    window.location.href = "/shopApi/shopCategoryPage?shopId=" + shopId;
}