import KanbanAPI from "../api/KanbanAPI.js";
import DropZone from "./dropzone.js";
import Item from "./item.js";

export default class Column {
    constructor(id, title) {
        const topDropZone = DropZone.createDropZone();

        this.elements = {};
        this.elements.root = Column.createRoot();
        this.elements.title = this.elements.root.querySelector(".kanban__column-title");
        this.elements.items = this.elements.root.querySelector(".kanban__column-items");
        this.elements.addItem = this.elements.root.querySelector(".kanban__add-item");

        this.elements.root.dataset.id = id;
        this.elements.title.textContent = title;
        this.elements.items.appendChild(topDropZone);

        this.elements.addItem.addEventListener("click", () => {
            const newItem = KanbanAPI.insertItem(id, "");
            this.renderItem(newItem);
        });

        KanbanAPI.getItems(id).forEach(item => {
            this.renderItem(item);
        });
    }

    //Define HTML for column
    //return an html element as an object
    //containing basic structure of a column
    static createRoot() {
        const range = document.createRange();

        range.selectNode(document.body);

        return range.createContextualFragment(`
            <div class="kanban__column">
                <div class="kanban__column-title"></div>
                <div class="kanban__column-items"></div>
                <button class="kanban__add-item" type="button">+ Add</button>
            </div>
        `).children[0]; //refers to kanban__column div defined above...
    }

    renderItem(data) {
        const item = new Item(data.id, data.content);
        this.elements.items.appendChild(item.elements.root);
    }
}