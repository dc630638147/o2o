$(function () {
    //封装轮播图信息
    var rollingImgUrl = '/headLineApp/getHeadLineList'
    var parentShopCategory = '/app/shopCategory/getParentCategory'
    $.getJSON(rollingImgUrl, function (res) {
        if (res.status == 200) {
            var data = res.data;
            var contHtml = '';
            $.each(data, function (index, obj) {
                var html = '<div class="swiper-slide img-wrap">' +
                    '<img class="banner-img" src="http://localhost:8080' + obj.lineImg + '" alt="' + obj.lineName + '"></div>';
                contHtml = contHtml + html;
            })
            $('#swiper-wrapper').html(contHtml);
            //轮播
            $(".swiper-container").swiper({
                autoplay: 1000,
                autoplayDisableOnInteraction: false
            });
        }
    })

    //一级分类展示
    $.getJSON(parentShopCategory, function (res) {
        if (res.status == 200) {
            var data = res.data;
            var contHtml = '';
            $.each(data, function (index, obj) {
                var html = '<div class="col-50 shop-classify" onclick="enterShopList('+obj.shopCategoryId+')" data-category=' + obj.shopCategoryId + '>'
                    + '<div class="word">'
                    + '<p class="shop-title">' + obj.shopCategoryName + '</p>'
                    + '<p class="shop-desc">' + obj.shopCategoryDesc + '</p>'
                    + '</div>'
                    + '<div class="shop-classify-img-warp">'
                    + '<img class="shop-img" src="http://localhost:8080' + obj.shopCategoryImg + '">'
                    + '</div>'
                    + '</div>';
                contHtml = contHtml + html;
            });
            $('#sc-row').html(contHtml);
        }
    })
})

function enterShopList(parentId) {
    window.location.href = '/index/shopList?parentId='+parentId;
}
