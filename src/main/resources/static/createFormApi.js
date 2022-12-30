let host = "http://15.164.54.82/api/v1/"
// TODO : 생각해보니까 응답을 볼 수 있는 사람은 제한되어야하는데... 이 페이지는 정적이라 누구에게나 동일하게 보인다.
let formId;

const postFormButton = document.querySelector("#postFormButton");
let myModal = new bootstrap.Modal(document.getElementById('myModal'), {
    keyboard: false
})
postFormButton.addEventListener("click", () => {
    if (!inputValidation()) {
        return;
    }

    if (checkSetting()) {
        myModal.show();
        fetch(host + "form", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                description: document.getElementById("description").value,
                startTime: document.getElementById("startTime").value,
                winnersNumber: document.getElementById("winnersNumber").value,
                questionPostReqs: createQuestions()
            })
        })
            .then((response) => response.text())
            .then((data) => {
                let formUrlInput = document.querySelector("#formUrl");
                formUrlInput.value = host + "form/" + data;
                formId = data;
            })
            .catch(err => {
                console.log('Fetch Error', err);
            });
    }
})

function checkSetting() {
    if (!document.getElementById("startTime").value ||
        !document.getElementById("winnersNumber").value) {
        alert("설정값은 필수입니다. 설정값 추가 후 다시 시도해주세요.");
        return false;
    }
    return true;
}

function inputValidation() {
    let start = new Date(document.getElementById('startTime').value).getTime();
    let now = new Date().getTime();
    if (start <= now) {
        alertBoot("시작시간은 현재 시간 이후부터 가능합니다.", 'danger');
        return false;
    }
    return true;
}

function createQuestions() {
    let questionList = [];
    for (let i = 0; i <= idNum; i++) {
        let questionInput = document.getElementById("question" + i);
        let question = {};
        let contents = [];
        question.title = questionInput.value;

        let fc = document.getElementById("formContent" + i);
        let selectChild = fc.childNodes[1].childNodes[1].childNodes[1].childNodes[3].childNodes[1];
        if (selectChild.options.selectedIndex === 0) {
            question.type = "TEXT";
        } else {
            question.type = "CHECKBOX";
        }

        let checkboxChildNodeCnt = fc.childNodes[1].childNodes[1].childNodes[1].childNodes[5].childElementCount;
        for (let j = 0; j < checkboxChildNodeCnt - 1; j++) {
            const contentValue = fc.childNodes[1].childNodes[1].childNodes[1].childNodes[5].childNodes[j].querySelector('input.form-control').value;
            contents.push(contentValue);
        }

        question.checkboxList = contents;
        questionList.push(question);
    }
    return questionList;
}

function getAnswerList() {
    let answerBody = document.getElementById('answerBody');

    if (formId == null) {
        answerBody.innerText = "응답을 기다리는 중입니다.";
        return;
    } else {
        let newWindow = window.open();
    }

    fetch(host + "answer" + formId, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                response.text();
            }
        })
        .then((data) => {
            answerBody.innerText = data;
        })
        .catch(err => {
            console.log('Fetch Error', err);
            document.getElementsByTagName('body').innerText = err;
        });
}