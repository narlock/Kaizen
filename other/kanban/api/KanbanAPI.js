export default class KanbanAPI {
    static getItems(columnId) {
        const column = read().find(column => column.id == columnId);

        if(!column) {
            return [];
        }

        return column.items;
    }

    static insertItem(columnId, content) {
        const data = read();
        const column = data.find(column => column.id == columnId);
        const item = {
            id: Math.floor(Math.random() * 10000000), //change this to an incremental id?
            content
        };

        if(!column) {
            throw new Error("[KanbanAPI] Error: Column does not exist");
        }

        column.items.push(item);
        save(data);

        return item;
    }

    static updateItem(itemId, newProps) {
        const data = read();
        
        //array destructuring
        const [item, currentColumn] = (() => {
            for(const column of data) {
                const item = column.items.find(item => item.id == itemId);
                if(item) {
                    return [item, column];
                }
            }
        })();

        if(!item) {
            throw new Error("[KanbanAPI] Error: Item not found")
        }

        item.content = newProps.content === undefined ? item.content : newProps.content;

        //update column and position
        if (newProps.columnId !== undefined && newProps.position !== undefined) {
            const targetColumn = data.find(column => column.id == newProps.columnId);
            if(!targetColumn) {
                throw new Error("[KanbanAPI] Error: Target column not found");
            }

            //delete the item from it's current column
            currentColumn.items.splice(currentColumn.items.indexOf(item), 1);

            //move item into it's new column and position
            targetColumn.items.splice(newProps.position, 0, item);
        }

        save(data);
    }

    static deleteItem(itemId) {
        const data = read();

        for(const column of data) {
            const item = column.items.find(item => item.id == itemId);

            if(item) { column.items.splice(column.items.indexOf(item), 1); }
        }

        save(data);
    }
}

//read from local storage
function read() {

    //TODO get the information from the sql database, return as json object

    //remove this code when I can get that here
    const json = localStorage.getItem('kanban-data');

    //first time using the kanban, we can return default data
    if(!json) {
        return [
            {
                id: 1,
                items: [{id: 727, content: "WYSI"}]
            },
            {
                id: 2,
                items: []
            },
            {
                id: 3,
                items: []
            }
        ];
    }

    return JSON.parse(json);
}

//save to local storage
function save(data) {
    localStorage.setItem("kanban-data", JSON.stringify(data));
}

