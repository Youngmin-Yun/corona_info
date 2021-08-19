$(function () {
    $("#continent_select").change(function () {
        let continent = $("#continent_select").find("option:selected").val()
        console.log(continent)
        getInternationalInfo(continent, "")
    })
    getInternationalInfo("아시아", "")

    function getInternationalInfo(continent, date) {
        let url = "/api/international/continent?continent=" + continent + "&date=" + date
        if (date != undefined && date != null && date != "") {
            url += "&date=" + date
        }
        $.ajax({
            type: "get",
            url: url,
            success: function (r) {
                console.log(r);
                $(".country_table tbody").html("")
                if (r.list != null) {
                    for (let i = 0; i < r.list.length; i++) {
                        let tag =
                            '<tr>' +
                            '<td id = "country_name">' + r.list[i].nationNm + '</td>' +
                            '<td id = "natDefCnt">' + comma(r.list[i].natDefCnt) + ' 명</td>' +
                            '<td id = "natDeathCnt">' + comma(r.list[i].natDeathCnt) + ' 명</td>' +
                            '<td id = "stdDt">' + r.list[i].createDt + '</td>' +
                            '</tr>'
                        $(".country_table tbody").append(tag);
                    }
                }
            }
        })
    }
})

function comma(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}