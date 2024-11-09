const sidebar = document.querySelector(".sidebar");
const sidebarOpenBtn = document.querySelector("#sidebar-open");
const sidebarCloseBtn = document.querySelector("#sidebar-close");
const sidebarLockBtn = document.querySelector("#lock-icon");
const sidebarClosedIcon = document.querySelector("#sidebar-closed-icon");

const toggleLock = () => {
  sidebar.classList.toggle("locked");
  if (!sidebar.classList.contains("locked")) {
    sidebar.classList.add("hoverable");
    sidebarLockBtn.classList.replace("bx-lock-alt", "bx-lock-open-alt");
  } else {
    sidebar.classList.remove("hoverable");
    sidebarLockBtn.classList.replace("bx-lock-open-alt", "bx-lock-alt");
  }
};

const hideSidebar = () => {
  if (sidebar.classList.contains("hoverable")) {
    sidebar.classList.add("close");
    sidebarCloseBtn.style.display = "block";
    sidebarClosedIcon.style.display = "none";
  }
};

const showSidebar = () => {
  if (sidebar.classList.contains("hoverable")) {
    sidebar.classList.remove("close");
    sidebarCloseBtn.style.display = "none";
    sidebarClosedIcon.style.display = "block";
  }
};

const toggleSidebar = () => {
  sidebar.classList.toggle("close");
  
  if (sidebar.classList.contains("close")) {
    sidebarCloseBtn.style.display = "block";
    sidebarClosedIcon.style.display = "none";
  } else {
    sidebarCloseBtn.style.display = "none";
    sidebarClosedIcon.style.display = "block";
  }
};

sidebarLockBtn.addEventListener("click", toggleLock);
sidebar.addEventListener("mouseleave", hideSidebar);
sidebar.addEventListener("mouseenter", showSidebar);
sidebarCloseBtn.addEventListener("click", toggleSidebar);
sidebarClosedIcon.addEventListener("click", toggleSidebar);

if (window.innerWidth < 800) {
  sidebar.classList.add("close");
  sidebar.classList.remove("locked");
  sidebar.classList.remove("hoverable");
}

var metaTotal = /*[[${metaTotal}]]*/ 0;

var ctx = document.getElementById('comidaPreferida_bar').getContext('2d');
var comidaPreferida_bar = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Mariana Soares', 'Gabriel Lima', 'Carla Ferreira'],
        datasets: [
            {
                label: 'Meta',
                data: [100, 0, 35],
                backgroundColor: '#C21712',
                borderColor: '#C21712',
                borderWidth: 1
            },
            {
                label: 'Meta Realizada',
                data: [105, 97, 36],
                backgroundColor: '#583F99',
                borderColor: '#583F99',
                borderWidth: 1
            }
        ]
    },
    options: {
        indexAxis: 'y',
        scales: {
            x: {
                beginAtZero: true,
                ticks: {
                    color: 'rgba(0,0,0,1)',
                    font: { size: 14 }
                }
            },
            y: {
                ticks: {
                    color: 'rgba(0,0,0,1)',
                    font: { size: 14 }
                }
            }
        },
        plugins: {
            tooltip: {
                callbacks: {
                    label: function(tooltipItem) {
                        return tooltipItem.dataset.label + ': ' + tooltipItem.raw;
                    }
                }
            }
        }
    }
});

var ctxBubble = document.getElementById('comidaPreferida_Bolhas').getContext('2d');
const dataBubble = {
    datasets: [
        {
            label: 'Participantes',
            data: [
                { x: 1, y: 100, r: 10, nome: 'Carla Ferreira', premiacao: 180 },
                { x: 2, y: 200, r: 15, nome: 'Mariana Soares', premiacao: 210 },
                { x: 3, y: 300, r: 20, nome: 'Gabriel Lima', premiacao: 291 }
            ],
            backgroundColor: '#241F4A',
            borderColor: '#241F4A',
            borderWidth: 1
        }
    ]
};

const configBubble = {
    type: 'bubble',
    data: dataBubble,
    options: {
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Participantes',
                    color: 'black',
                    font: { size: 14 }
                },
                ticks: {
                    color: 'black',
                    callback: function(value) {
                        const nomes = ['Mariana Soares', 'Gabriel Lima', 'Carla Ferreira'];
                        return nomes[value - 1];
                    }
                }
            },
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Valor da Premiação',
                    color: 'black',
                    font: { size: 14 }
                },
                ticks: {
                    color: 'black',
                    font: { size: 14 }
                }
            }
        },
        plugins: {
            tooltip: {
                callbacks: {
                    label: function(context) {
                        return context.raw.nome + ': R$' + context.raw.premiacao;
                    }
                }
            }
        }
    }
};

var comidaPreferida_Bolhas = new Chart(ctxBubble, configBubble);

var ctx = document.getElementById('melhoresEmpresas_bar').getContext('2d');

const data = {
    labels: ['Lucas Andrade', 'Mariana Soares'],
    datasets: [
        {
            label: 'Meta',
            data: [21, 9],
            backgroundColor: '#C21712', // Cor para "Meta"
            borderColor: '#C21712',
            borderWidth: 1
        },
        {
            label: 'Meta Realizada',
            data: [45, 10],
            backgroundColor: '#583F99', // Cor para "Meta Realizada"
            borderColor: '#583F99',
            borderWidth: 1
        },
        {
            label: 'Valor da Premiação',
            data: [30, 15], // Insira o valor da premiação para cada participante
            backgroundColor: '#FF3300', // Cor para "Valor da Premiação"
            borderColor: '#FF3300',
            borderWidth: 1
        }
    ]
};

const config = {
    type: 'bar',
    data: data,
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    label: function(context) {
                        let label = context.dataset.label || '';
                        if (label) {
                            label += ': ';
                        }
                        label += context.raw;
                        return label;
                    }
                }
            }
        },
        responsive: true,
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Participantes',
                    color: 'black',
                    font: 14
                },
                ticks: {
                    color: 'black',
                    font: 14
                },
                barPercentage: 0.4, // Ajusta o tamanho da barra para não ficarem tão grossas
                categoryPercentage: 0.4 // Ajusta a largura das categorias
            },
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Valores',
                    color: 'black',
                    font: 12
                },
                ticks: {
                    color: 'black',
                    font: 12
                }
            }
        }
    }
};
var melhoresEmpresas_bar = new Chart(ctx, config);

const datas = {
    labels: ["Lucas Andrade", "Mariana Soares", "Gabriel Lima"],
    datasets: [
        {
            label: "Meta Volume",
            data: [29, 20, 110],
            backgroundColor: "#C21712"
        },
        {
            label: "Real Volume",
            data: [30, 25, 112],
            backgroundColor: "#04146D"
        },
        {
            label: "Meta Positivação",
            data: [8, 8, null],  // null representa ausência de valor para Gabriel
            backgroundColor: "#583F99"
        },
        {
            label: "Real Positivação",
            data: [10, 6, null],
            backgroundColor: "#00FF1A"
        }
    ]
};

const config3 = {
    type: "bar",
    data: datas,
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    label: function(context) {
                        let label = context.dataset.label || '';
                        if (label) {
                            label += ': ';
                        }
                        label += context.raw;
                        return label;
                    }
                }
            }
        },
        responsive: true,
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Participantes',
                    color: 'black',
                    font: 14
                },
                ticks: {
                    color: 'black',
                    font: 14
                },
                barPercentage: 0.4, // Ajusta o tamanho da barra para não ficarem tão grossas
                categoryPercentage: 0.4 // Ajusta a largura das categorias
            },
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Valores',
                    color: 'black',
                    font: 12
                },
                ticks: {
                    color: 'black',
                    font: 12
                }
            }
        }
    }
};

const comeMelhorBar = new Chart(
    document.getElementById("comeMelhorBar"),
    config3
);

// Configurando os dados
const dataMelhor = {
    labels: ["Lucas Andrade", "Mariana Soares", "Gabriel Lima"], // Participantes
    datasets: [
        {
            label: "Valor da Premiação",
            data: [1000, 1000, 1000], // Valores de premiação
            backgroundColor: [
                "rgba(75, 192, 192, 0.6)", // Cor para Lucas Andrade
                "rgba(153, 102, 255, 0.6)", // Cor para Mariana Soares
                "rgba(255, 159, 64, 0.6)"  // Cor para Gabriel Lima
            ],
            borderColor: [
                "rgba(75, 192, 192, 1)", 
                "rgba(153, 102, 255, 1)", 
                "rgba(255, 159, 64, 1)"
            ],
            borderWidth: 1
        }
    ]
};

// Configurando o gráfico
const config4 = {
    type: "doughnut", // Altera o tipo para 'pie' para gráfico de pizza
    data: dataMelhor,
    options: {
        responsive: true,
        plugins: {
            tooltip: {
                callbacks: {
                    label: function(context) {
                        let label = context.label || '';
                        if (label) {
                            label += ': ';
                        }
                        label += `R$ ${context.raw}`;
                        return label;
                    }
                }
            },
            legend: {
                position: "top"
            }
        }
    }
};

// Renderizando o gráfico
const destaqueChart = new Chart(
    document.getElementById("destaque"),
    config4
);


