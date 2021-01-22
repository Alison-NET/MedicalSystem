


document.addEventListener("DOMContentLoaded", function(){

    fetch('/api/employee-portal/account/setup')
        .then((response) => {
            return response.json();
        })
        .then((constants) => {

            onFetchSetupListeners(constants);

            console.log(constants);
        })
        .catch(err=>console.log(err));



    function onFetchSetupListeners(constants) {

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

            let elementClicked = evt.target;


            if(elementClicked.classList.contains("add-pick-up-time-btn")){

                let specimenPickUpDayTimes = elementClicked.closest(".specimen-pick-ups-item")
                let nodes = Array.prototype.slice.call(specimenPickUpDayTimes.parentElement.querySelectorAll(".specimen-pick-ups-item"));
                let dayIndex = nodes.indexOf(specimenPickUpDayTimes);

                let specimenPickUpTimes = specimenPickUpDayTimes.querySelector(".specimen-pick-up-times");

                let specimenPickUpTimesAmount = specimenPickUpTimes.children.length;

                if(specimenPickUpTimesAmount < constants['maxPickUpTimes']){
                    specimenPickUpTimes.insertAdjacentHTML("beforeend",
                        `<div class="specimen-pick-up-times-item">

                                <input class="form-in-item" type="time" 
                                    id="specimenPickUpDayTimes${dayIndex}.pickUpTimes${specimenPickUpTimesAmount}.pickUpTime" 
                                    name="specimenPickUpDayTimes[${dayIndex}].pickUpTimes[${specimenPickUpTimesAmount}].pickUpTime">

                                <button type="button" class="remove-pick-up-time-btn">Remove</button>

                            </div>`)
                }

            }
            else if(elementClicked.classList.contains("remove-provider-btn")) {



                let elementToDelete = elementClicked.closest('.provider-row');
                elementToDelete.parentElement.removeChild(elementToDelete);

                let parentElementToUpdateChildrenIds = document.querySelector(".providers-table");
                updateElementInnerAttributesIndexes(parentElementToUpdateChildrenIds,
                    "providers",
                    'input, select');


            }else if(elementClicked.classList.contains("remove-pick-up-time-btn")){


                let elementToDelete = elementClicked.closest('.specimen-pick-up-times-item');
                let elementToUpdateChildrenIds = evt.target.closest(".specimen-pick-up-times");
                elementToDelete.parentElement.removeChild(elementToDelete);

                updateElementInnerAttributesIndexes(elementToUpdateChildrenIds,
                    "pickUpTimes",
                    'input');

            }
        });
    }

    function updateElementInnerAttributesIndexes(parentElem,
                                                 indexOf,
                                                 childInnerElemsSelector,
                                                 attributesToUpdate = ["id", "name"],
                                                 startIndex = 0){

        for(let providersChildNode of parentElem.children){
            let elementsToUpdate = providersChildNode.querySelectorAll(childInnerElemsSelector);
            for(let elementToUpdate of elementsToUpdate){
                updateElementAttrsIndex(elementToUpdate, attributesToUpdate, startIndex, indexOf);
            }
            ++startIndex;
        }
    }

    function updateElementAttrsIndex(element, attributes, index, indexOf){
        for(let attrName of attributes){
            if(element.hasAttribute(attrName)){
                let attributeVal  = element.getAttribute(attrName);

                let regexp = new RegExp(`(${indexOf}.?)(\\d+)`, "g")

                attributeVal = attributeVal.replace(regexp, "$1" + index);
                element.setAttribute(attrName, attributeVal);
            }
        }
    }



});

