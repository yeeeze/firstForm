let idNum = 0;
// - 버튼
function removeForm() {
    let formContent = document.getElementById("formContent" + idNum);
    formContent.remove();
    idNum--;
}

// +버튼을 누르면 동적으로 폼을 추가해주고 싶어
function createForm() {
    let formContent = document.getElementById("formContent" + idNum);
    let newForm = formContent.cloneNode(true);
    idNum++;
    newForm.id = 'formContent' + idNum;

    let section = document.getElementById("section");
    section.appendChild(newForm);

    let fc = document.getElementById("formContent" + idNum);
    let parent = fc.childNodes[1].childNodes[1].childNodes[1];
    parent.id = 'parent' + idNum;

    const input = fc.childNodes[1].childNodes[1].childNodes[1].childNodes[1].childNodes[1].childNodes[1];
    input.id = "question" + idNum;
    input.value = null;

    let selectChild = fc.childNodes[1].childNodes[1].childNodes[1].childNodes[3].childNodes[1]
    selectChild.id = "selectType" + idNum;
    selectChild.innerHTML =
        "                                <option selected value=\"1\">단답형</option>\n" +
        "                                <option value=\"2\">객관식 질문</option>";

    let childDiv = parent.childNodes[5];
    parent.removeChild(childDiv);
    let newDiv = document.createElement("div");
    insertInitTextAnswer(newDiv, parent, idNum);

    const id = idNum;
    selectChild.addEventListener('change', (event) => selectChange(event, id));
}

// select의 값에 따라 div#formContent의 내용을 바꿔주고싶어 (parent의 자식으로 추가)
document.addEventListener('DOMContentLoaded', () => {
    let parent = document.getElementById("parent0");
    let newDiv = document.createElement("div");
    insertInitTextAnswer(newDiv, parent, 0);

    let select = document.getElementById("selectType0");
    select.addEventListener('change', (event) => selectChange(event, 0));
})

function selectChange(event, cnt) {
    let index = event.currentTarget.options.selectedIndex;

    // 기존 div 제거
    let parent = document.getElementById("parent" + cnt);
    let childDiv = document.getElementById("childDiv" + cnt);
    if (childDiv != null) {
        parent.removeChild(childDiv);
    }

    let newDiv = document.createElement("div");
    newDiv.id = 'childDiv' + cnt;
    if (index === 0) {
        newDiv.style.color = "gray";
        newDiv.style.fontsize = '15px';
        newDiv.innerHTML = "단답형 텍스트";

        let hr = document.createElement("hr");
        hr.style.borderTop = '1px';
        hr.style.borderStyle = 'dotted';
        hr.style.width = '50%';
        newDiv.appendChild(hr);
        parent.appendChild(newDiv);
    } else {
        newDiv.innerHTML =
            "<div class=\"input-group mb-3\" class=\"col-8\" id=\"addCheckBoxDiv" + 1 + "\" style=\"width: 50%;\">\n"+
            "    <div class=\"input-group-text\">\n" +
            "        <input class=\"form-check-input mt-0\" type=\"checkbox\" value=\"\" aria-label=\"Checkbox for following text input\">\n" +
            "    </div>\n" +
            "    <input type=\"text\" class=\"form-control\" aria-label=\"Text input with checkbox\">\n" +
            "</div>" +
            "<div class=\"input-group mb-3\" class=\"col-8\" id=\"lastCheckbox" + cnt + "\" style=\"width: 50%;\">\n"+
            "    <div class=\"input-group-text\">\n" +
            "        <input class=\"form-check-input mt-0\" type=\"checkbox\" value=\"\" aria-label=\"Checkbox for following text input\">\n" +
            "    </div>\n" +
            "    <input type=\"button\" id=\"" + cnt + "\" class=\"form-control\" aria-label=\"Text input with checkbox\" value=\"옵션 추가\" " +
            "onclick='addCheckBoxContentDiv(event)' style=\"color: gray; text-align: left\">" +
            "</div>";
        parent.appendChild(newDiv);
    }
}

let addCheckBoxCnt = 1;
function addCheckBoxContentDiv(event) {
    let inputButtonId = event.currentTarget.id;

    let lastCheckbox = document.getElementById("lastCheckbox" + inputButtonId);
    let newDiv = document.createElement("div");
    addCheckBoxCnt++;
    newDiv.id = 'addCheckBoxDiv' + addCheckBoxCnt;
    newDiv.className = 'input-group mb-3';
    newDiv.style.width = '50%';
    newDiv.innerHTML =
        "<div class=\"input-group-text\">\n" +
        "   <input class=\"form-check-input mt-0\" type=\"checkbox\" value=\"\" aria-label=\"Checkbox for following text input\">\n" +
        "</div>\n" +
        "<input type=\"text\" class=\"form-control\" aria-label=\"Text input with checkbox\">";
    lastCheckbox.before(newDiv);
}

function insertInitTextAnswer(newDiv, parent, cnt) {
    newDiv.id = 'childDiv' + cnt;
    newDiv.style.color = 'gray';
    newDiv.style.fontsize = '15px';
    newDiv.innerHTML = "단답형 텍스트";

    let hr = document.createElement("hr");
    hr.style.borderTop = '1px';
    hr.style.borderStyle = 'dotted';
    hr.style.width = '50%';
    newDiv.appendChild(hr);
    parent.appendChild(newDiv);
}

/**
 * 페이지 화면 분리 (질문 응답 설정)
 */
let settingBox = document.getElementById("setting");
let questionBox = document.getElementById("notSetting");
let answerBox = document.getElementById('answerPage');

let settingButton = document.getElementById("settingButton");
let questionButton = document.getElementById("questionButton");
let answerButton = document.getElementById('answerButton');
let plusButton = document.getElementById('float');
settingButton.addEventListener('click', () => {
    settingBox.style.display = 'block';
    questionBox.style.display = 'none';
    answerBox.style.display = 'none';
    plusButton.style.display = 'none';
})

questionButton.addEventListener('click', () => {
    settingBox.style.display = 'none';
    questionBox.style.display = 'block';
    answerBox.style.display = 'none';
    plusButton.style.display = 'block';
});

answerButton.addEventListener('click', () => {
    settingBox.style.display = 'none';
    questionBox.style.display = 'none';
    answerBox.style.display = 'block';
    plusButton.style.display = 'none';
    getAnswerList();
})

/**
 * 설정값 validation
 */

function numberMax(e) {
    if (e.value > 10000 || e.value < 1) {
        if (document.getElementById('alert') === null) {
            alertBoot("당첨자 수는 1명 이상 1만명 이하로 입력해주세요.", 'danger');
        }
    } else {
        if (document.getElementById('alert') != null) {
            document.getElementById('alert').remove();
        }
    }
}

const alertBoot = (message, type) => {
    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
        `<div id="alert" class="alert alert-${type} alert-dismissible" role="alert">`,
        `   <div>${message}</div>`,
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('')

    document.getElementById('setting').appendChild(wrapper);
}