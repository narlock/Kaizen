import Column from "./column.js";

export default class KanbanView {
    constructor(root) {
        this.root = root;

        KanbanView.columns().forEach(column => {
            //TODO create an instance of column class
            const columnView = new Column(column.id, column.title);

            this.root.appendChild(columnView.elements.root);
        });
    }

    static columns() {
        return [
            {
                id: 1,
                title: "Not started"
            },
            {
                id: 2,
                title: "In progress"
            },
            {
                id: 3,
                title: "Completed"
            }
        ];
    }
}