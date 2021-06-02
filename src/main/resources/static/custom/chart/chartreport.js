new Chart(document.getElementById("radar-chart"), {
    type: 'radar',
    data: {
      labels: ["Stage1", "Stage2", "Stage3", "Stage4", "Stage5"],
      datasets: [{
          label: "보안",
          fill: true,
          backgroundColor: "rgba(255,99,132,0.2)",
          borderColor: "rgba(255,99,132,1)",
          pointBorderColor: "#fff",
          pointBackgroundColor: "rgba(255,99,132,1)",
          pointBorderColor: "#fff",
          data: [2, 4, 10, 5, 7]
        },{
          label: "기능",
          fill: true,
          backgroundColor: "rgba(179,181,198,0.2)",
          borderColor: "rgba(179,181,198,1)",
          pointBorderColor: "#fff",
          pointBackgroundColor: "rgba(179,181,198,1)",
          data: [5, 3, 1, 7, 9]
        }
      ]
    },
    options: {
	maintainAspectRatio: false,
      title: {
        display: true,
        text: '학습결과'
      }
    }
});
new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
      labels: ["Stage1", "Stage2", "Stage3", "Stage4", "Stage5"],
      datasets: [{
          label: "보안",
          fill: true,
          backgroundColor: "rgba(255,99,132,1)",
          data: [2, 4, 10, 5, 7]
        },{
          label: "기능",
          fill: true,
           backgroundColor: "rgba(179,181,198,0.2)",
          data: [5, 3, 1, 7, 9]
        }
      ]
    },
    options: {
	maintainAspectRatio: false,
      title: {
        display: true,
        text: '학습결과'
      }
    }
});