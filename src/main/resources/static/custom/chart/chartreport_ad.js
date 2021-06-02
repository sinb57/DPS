new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
      labels: ["Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec."],
      datasets: [{
          label: "회원 수",
          fill: true,
          backgroundColor: "#eadbfa",
          data: [1, 2, 4, 10, 5, 3, 6, 9, 3, 8, 10, 7]
        }
      ]
    },
    options: {
	maintainAspectRatio: false,
      title: {
        display: true,
      }
    }
});