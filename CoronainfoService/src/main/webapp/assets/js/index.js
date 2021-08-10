$(function () {

    $.ajax({
        type: "get",
        url: "/api/coronaInfo/today",
        success: function (r) {
            console.log(r)
            $("#accExamCnt").html(r.data.strAccExamCnt)
            $("#decideCnt").html(r.data.strDecideCnt)
            let ctx2 = $("#confirmed_chart")
            let confirmed_chart = new Chart(ctx2, {
                type: 'pie',
                options: {
                    responsive: false
                },
                data: {
                    labels: ["양성", "음성"],
                    datasets: [{
                        label: "양성/음성",
                        data: [r.data.decideCnt, r.data.examCnt-r.data.decideCnt],
                        backgroundColor: ['green', 'blue', 'red']
                    }]
                },
            })
        }
    })
    let ctx = $("#regional_status")
    let regionalChart = new Chart(ctx, {
        type: 'bar',
        options: {
            responsive: false
        },
        data: {
            labels: ["전국", "서울특별시", "부산광역시", "대구광역시", "광주광역시", "대전광역시", "인천광역시", "울산광역시",
                "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도",
                "제주특별자치도"
            ],
            datasets: [{
                label: "2021-08-09 신규확진",
                data: [415, 408, 86, 65, 123, 88, 30, 68, 24, 42, 39, 19, 25, 21, 14, 11, 1],
                backgroundColor: ['green', 'blue', 'red']
            }]
        }
    })
    // let ctx2 = $("#confirmed_chart")
    // let confirmed_chart = new Chart(ctx2, {
    //     type:'pie',
    //     options:{
    //         responsive:false
    //     },
    //     data:{
    //         labels:["양성", "음성"],
    //         datasets:[{
    //             label:"양성/음성",
    //             data:[100,200],
    //             backgroundColor:['green','blue','red']
    //         }]
    //     },  
    // })
    let ctx3 = $("#vaccine_chart")
    let vaccineChart = new Chart(ctx3, {
        type: 'bar',
        options: {
            responsive: false
        },
        data: {
            labels: ["전국", "서울특별시", "부산광역시", "대구광역시", "광주광역시", "대전광역시", "인천광역시", "울산광역시",
                "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도",
                "제주특별자치도"
            ],
            datasets: [{
                    label: "2021-08-09 1차 접종현황",
                    data: [415, 408, 86, 65, 123, 88, 30, 68, 24, 42, 39, 19, 25, 21, 14, 11, 1],
                    backgroundColor: ['green', 'blue', 'red']
                },
                {
                    label: "2021-08-09 2차 접종현황",
                    data: [415, 408, 86, 65, 123, 88, 30, 68, 24, 42, 39, 19, 25, 21, 14, 11, 1],
                    backgroundColor: ['green', 'blue', 'red']
                }
            ]
        }
    })
})