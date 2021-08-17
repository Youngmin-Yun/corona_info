$(function () {
    $("#region_select").change(function(){
        let region = $(this).find("option:selected").val()
        getCoronaSidoInfo(region)
        getCoronaVeccineInfo(region)
    })
    getCoronaSidoInfo("서울")
    getCoronaVeccineInfo("서울")
    function getCoronaSidoInfo(sido) {
        $.ajax({
            type: "get",
            url: "http://localhost:8077/api/regional?region="+sido,
            success: function (r) {
                console.log(r)
                $("#accDecideCnt").html(r.data.defCnt)
                $("#newDecideCnt").html(r.data.incDec)
                $("#isolateCnt").html(r.data.isolIngCnt)
                $("#clearIsolateCnt").html(r.data.isolClearCnt)
                
                $("#covidDanger span").css("display", "none")
                let danger = r.data.incDec + r.data.diff
                if (danger >= 200) {
                    $("#covidDanger span").eq(3).css("display", "inline").css("color", "#ff0000")
                } else if (danger >= 100) {
                    $("#covidDanger span").eq(2).css("display", "inline").css("color", "#ffcc66")
                } else if (danger >= 10) {
                    $("#covidDanger span").eq(1).css("display", "inline").css("color", "#ffcc66")
                } else {
                    $("#covidDanger span").eq(0).css("display", "inline").css("color", "#ffff99")
                }
            }
        })
    }
    function getCoronaVeccineInfo(sido){
        if(sido == "서울") sido = "서울특별시"
        if(sido == "부산") sido = "부산광역시"
        if(sido == "대구") sido = "대구광역시"
        if(sido == "인천") sido = "인천광역시"
        if(sido == "광주") sido = "광주광역시"
        if(sido == "대전") sido = "대전광역시"
        if(sido == "울산") sido = "울산광역시"
        if(sido == "세종") sido = "세종특별자치시"
        if(sido == "경기") sido = "경기도"
        if(sido == "강원") sido = "강원도"
        if(sido == "충북") sido = "충청북도"
        if(sido == "충남") sido = "충청남도"
        if(sido == "전북") sido = "전라북도"
        if(sido == "전남") sido = "전라남도"
        if(sido == "경북") sido = "경상북도"
        if(sido == "경남") sido = "경상남도"
        if(sido == "제주") sido = "제주특별자치도"
        $.ajax({
            type: "get",
            url: "http://localhost:8077/api/corona/vaccineStatus?region="+sido,
            success: function (r) {
                console.log(r)
                $("#vaccineFirstCnt").html(r.data.firstshot)
                $("#vaccineSecondCnt").html(r.data.secondshot)
            }
        })
    } 
})