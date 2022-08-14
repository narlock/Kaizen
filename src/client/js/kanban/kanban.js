import KanbanView from "./view/kanban-view.js";

export default class Kanban {

    constructor() {
        new KanbanView(
            document.querySelector(".kanban")
        );
    }
    
}
