


document.addEventListener("DOMContentLoaded", function(){

    fetch('/api/employee-portal/account/setup')
        .then((response) => {
            return response.json();
        })
        .then((constants) => {


            let addProviderBtn = document.querySelector(".add-provider-btn");

            addProviderBtn.addEventListener("click", ()=>{

                let providersTableNode = document.querySelector(".providers-table");
                let provsCount = providersTableNode.children.length;

                if( provsCount < constants['maxProviders']){

                    let options = '';
                    for(title of constants['titles']){
                        options+=`<option value="${title['id']}">${title['name']}</option>`;
                    }

                    providersTableNode.insertAdjacentHTML('beforeend',
                        `<tr class="provider-row">
                                <td>
                                    <select class="form-in-item" id="providers${provsCount}.title" name="providers[${provsCount}].title">
                                     ${options}
                                    </select>
                                </td>
                                <td>
                                    <span>First Name</span>
                                    <input type="text" id="providers${provsCount}.firstName" name="providers[${provsCount}].firstName"/>
                                </td>
                                <td>
                                    <span>Last Name</span>
                                    <input type="text" id="providers${provsCount}.lastName" name="providers[${provsCount}].lastName"/>
                                </td>

                                <td>
                                    <span>Email</span>
                                    <input type="email" id="providers${provsCount}.email" name="providers[${provsCount}].email"/>
                                </td>
                                <td>
                                    <span>NPI</span>
                                    <input type="text" placeholder="0000000000" id="providers${provsCount}.NPI" name="providers[${provsCount}].NPI"/>
                                </td>
                                <td>
                                    <button type="button" class="remove-provider-btn">Remove</button>
                                </td>
                            </tr>`);
                }
            });

            window.addEventListener("click", (evt) => {

                if(evt.target.classList.contains("remove-provider-btn")) {
                    let parentToDelete = evt.target.closest('tbody');
                    parentToDelete.parentElement.removeChild(parentToDelete);
                    console.log("remove clicked");

                    updateElementInnerAttributesIndexes(".providers-table",
                        'input, select',
                        ["id", "name"]);
                }
            });

            function updateElementInnerAttributesIndexes(parentElemSelector, innerElemsSelector, attributesToUpdate){
                let providersTableNode = document.querySelector(parentElemSelector);

                let index = 0;
                for(let providersChildNode of providersTableNode.children){
                    let elementsToUpdate = providersChildNode.querySelectorAll(innerElemsSelector);
                    for(let elementToUpdate of elementsToUpdate){
                        updateElementAttrsIndex(elementToUpdate, attributesToUpdate, index);
                    }
                    ++index;
                }
            }

            function updateElementAttrsIndex(element, attributes, index){
                for(let attrName of attributes){
                    if(element.hasAttribute(attrName)){
                        let attributeVal  = element.getAttribute(attrName);
                        attributeVal = attributeVal.replace(/\d+/g,index+"");
                        element.setAttribute(attrName, attributeVal);
                    }
                }
            }



            console.log(constants);
        })
        .catch(err=>console.log(err));




});

function addPickUpTime(specimenPickUpDayId){

    let pickUpTimesNode = document.querySelector(".pick-up-times-wrapper");
    let pickUpTimesCount = pickUpTimesNode.children.length;

    if( pickUpTimesCount < constants['maxPickUpTimes']){
        pickUpTimesNode.insertAdjacentHTML('beforeend',
            `<div class="pick-up-times-item">
                                    <input class="form-in-item" type="time" id="specimenPickUpDayTimes${specimenPickUpDayId}.pickUpTimes${pickUpTimesCount}.pickUpTime" 
                                    name="specimenPickUpDayTimes[${specimenPickUpDayId}].pickUpTimes[${pickUpTimesCount}].pickUpTime" />
                                    <button type="button" class="remove-pick-up-time">Remove</button>
                              </div>`);
    }
}