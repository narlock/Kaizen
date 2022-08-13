import KanbanAPI from "../api/KanbanAPI.js";

export default class DropZone {
    static createDropZone() {
        const range = document.createRange();

        range.selectNode(document.body);

        const dropZone = range.createContextualFragment(`
            <div class="kanban__dropzone"></div>
        `).children[0];

        dropZone.addEventListener("dragover", e => {
            e.preventDefault();
            dropZone.classList.add("kanban__dropzone--active");
        });

        dropZone.addEventListener("dragleave", () => {
            dropZone.classList.remove("kanban__dropzone--active");
        })

        dropZone.addEventListener("drop", e => {
            e.preventDefault();
            dropZone.classList.remove("kanban__dropzone--active");

            const columnElement = dropZone.closest(".kanban__column"); //gives html element for column that dropzone is inside of
            const columnId = Number(columnElement.dataset.id);
            const dropZonesInColumn = Array.from(columnElement.querySelectorAll(".kanban__dropzone"));
            const droppedIndex = dropZonesInColumn.indexOf(dropZone);
            const itemId = Number(e.dataTransfer.getData("text/plain"));
            const droppedItemElement = document.querySelector(`[data-id="${itemId}"]`);
            const insertAfter = dropZone.parentElement.classList.contains("kanban__item") ? dropZone.parentElement : dropZone; //if dropzone is apart of kanban item then append to parent

            if(droppedItemElement.contains(dropZone)) {
                return;
            }
            insertAfter.after(droppedItemElement);

            //updates the item in local storage when dropped
            KanbanAPI.updateItem(itemId, {
                columnId,
                position: droppedIndex
            });
        });

        return dropZone;
    }
}