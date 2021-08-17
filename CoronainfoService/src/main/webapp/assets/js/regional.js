$.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
});
$(function () {
    var coronaWeeksChart = new Chart($("#accDecideChart"),{
        type:"line",
        options:{
            responsive:false
        },
        data:{
            labels:null,
            datasets:[{
                label:'코로나 누적 확진자',
                data:null,
                backgroundColor:[
                    'rgba(30,30,225,0.7)'
                ]
            }]
        }
    })
    $("#date").datepicker()
    $("#date").datepicker("setDate", new Date())
    $("#date").change(function(){
        let region = $("#region_select").find("option:selected").val()
        let date = $("#date").val()
        getCoronaSidoInfo(region, date)
        getCoronaVeccineInfo(region, date)
    })
    $("#region_select").change(function(){
        let region = $("#region_select").find("option:selected").val()
        let date = $("#date").val()
        getCoronaSidoInfo(region, date)
        getCoronaVeccineInfo(region, date)
    })
    getCoronaSidoInfo("서울")
    getCoronaVeccineInfo("서울")
    function getCoronaSidoInfo(sido, date) {
        let url = "http://localhost:8077/api/regional?region="+sido
        if(date != undefined && date != null && date != ""){
            url += "&date="+date
        }
        $.ajax({
            type: "get",
            url: url,
            success: function (r) {
                console.log(r)
                if(r.coronaWeeksList != null){
                    let coronaWeeksLabel = new Array()
                    let coronaWeeksData = new Array()
                    for(let i = 0; i<r.coronaWeeksList.length; i++){
                        coronaWeeksLabel.push(r.coronaWeeksList[i].date)
                        coronaWeeksData.push(r.coronaWeeksList[i].defCnt)
                    }
                    coronaWeeksChart.data.datasets = new Array() // 데이터 셋 초기화
                    coronaWeeksChart.data.labels = coronaWeeksLabel // 레이블 교체
                    // 데이터 셋 추가
                    coronaWeeksChart.data.datasets.push({
                        label:'코로나 누적 확진자', data:coronaWeeksData,
                        backgroundColor:['rgba(30,30,225,0.7)']
                    }) 
                    // 차트 업데이트
                    coronaWeeksChart.update()
                    // var coronaWeeksChart = new Chart($("#accDecideChart"),{
                    //     type:"line",
                    //     options:{
                    //         responsive:false
                    //     },
                    //     data:{
                    //         labels:coronaWeeksLabel,
                    //         datasets:[{
                    //             label:'코로나 누적 확진',
                    //             data:coronaWeeksData,
                    //             backgroundColor:[
                    //                 'rgba(30,30,225,0.7)'
                    //             ]
                    //         }]
                    //     }
                    // })
                }
                if(r.dangerAge == null){
                    $("#dangerAge").html("-")    
                }
                else{
                    $("#dangerAge").html(r.dangerAge)
                }
                if(r.data == null){
                    $("#accDecideCnt").html("-")
                    $("#newDecideCnt").html("-")
                    $("#isolateCnt").html("-")
                    $("#clearIsolateCnt").html("-") 
                    $("#covidDanger span").css("display", "none")
                    $("#covidDanger span").eq(0).css("display", "inline").css("color", "#ffff99")
                    return
                }
                $("#accDecideCnt").html(r.data.strDefCnt)
                $("#newDecideCnt").html(r.data.strIncDec)
                $("#isolateCnt").html(r.data.strIsolingCnt)
                $("#clearIsolateCnt").html(r.data.strIsolClearCnt)
                
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
    var vaccineWeeksChart = new Chart($("#accVaccineChart"),{
        type:"line",
        options:{
            responsive:false
        },
        data:{
            labels:null,
            datasets:null
        }
    })
    function getCoronaVeccineInfo(sido, date){
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
        let url = "http://localhost:8077/api/corona/vaccineStatus?region="+sido
        if(date != undefined && date != null && date != ""){
            url += "&date="+date
        }
        $.ajax({
            type: "get",
            url: url,
            success: function (r) {
                console.log(r.vaccineList)
                if(r.vaccineList != null){
                    let vaccineListLabel = new Array()
                    let vaccineFirstList = new Array()
                    let vaccineSecondList = new Array()
                    for(let i = 0; i<r.vaccineList.length; i++){
                        vaccineListLabel.push(r.vaccineList[i].date)
                        vaccineFirstList.push(r.vaccineList[i].accFirstCnt)
                        vaccineSecondList.push(r.vaccineList[i].accSecondCnt)
                    }
                    vaccineWeeksChart.data.datasets = new Array() // 데이터 셋 초기화
                    vaccineWeeksChart.data.labels = vaccineListLabel // 레이블 교체
                    // 데이터 셋 추가
                    vaccineWeeksChart.data.datasets.push({
                        label:'백신 1차 접종 증가 추이', data:vaccineFirstList,
                        backgroundColor:['rgba(255,30,225,0.7)']
                    }) 
                    vaccineWeeksChart.data.datasets.push({
                        label:'백신 2차 접종 증가 추이', data:vaccineSecondList,
                        backgroundColor:['rgba(30,30,225,0.7)']
                    }) 
                    // 차트 업데이트
                    vaccineWeeksChart.update()
                }
                if(r.status == false){
                    $("#vaccineFirstCnt").html("-")
                    $("#vaccineSecondCnt").html("-")
                    return
                }
                $("#vaccineFirstCnt").html(r.data.firstshot)
                $("#vaccineSecondCnt").html(r.data.secondshot)
            }
        })
    } 
})