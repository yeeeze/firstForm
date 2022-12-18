let host = "http://localhost:8080/api/v1/"

const postFormButton = document.querySelector("#postFormButton");
postFormButton.addEventListener("click", () => {
    if (checkSetting()) {
        fetch(host + "form", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                description: document.getElementById("description").value,
                startTime: document.getElementById("startTime").value,
                endTime: document.getElementById("endTime").value,
                winnersNumber: document.getElementById("winnersNumber").value,
                questionPostReqs: createQuestions()
            })
        })
            .then((response) => response.text())
            .then((data) => {
                let formUrlInput = document.querySelector("#formUrl");
                formUrlInput.value = host + "form/" + data;
            })
            .catch(err => {
                console.log('Fetch Error', err);
            });
    }
})

function checkSetting() {
    if (!document.getElementById("startTime").value ||
        !document.getElementById("endTime").value ||
        !document.getElementById("winnersNumber").value) {
        alert("설정값은 필수입니다. 설정값 추가 후 다시 시도해주세요.");
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