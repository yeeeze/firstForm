// +버튼을 누르면 동적으로 폼을 추가해주고 싶어
let idNum = 0;
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
            "onclick='addCheckBoxDiv(event)' style=\"color: gray; text-align: left\">" +
            "</div>";
        parent.appendChild(newDiv);
    }
}

let addCheckBoxCnt = 1;
function addCheckBoxDiv(event) {
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

let settingBox = document.getElementById("setting");
let notSettingBox = document.getElementById("notSetting");
let settingButton = document.getElementById("settingButton");
let questionButton = document.getElementById("questionButton");
settingButton.addEventListener('click', () => {
    settingBox.style.display = 'block';
    notSettingBox.style.display = 'none';
})

questionButton.addEventListener('click', () => {
    settingBox.style.display = 'none';
    notSettingBox.style.display = 'block';
});


