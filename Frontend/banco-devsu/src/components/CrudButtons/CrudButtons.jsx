import React from "react";
import "./CrudButtons.css";

const CrudButtons = (props) => {
  const { id, children, type, className, onClickEdit, onClickDelete, ...rest } =
    props;
  return (
    <div className={className ? className : "tableCrudButtons"}>
      <button className="editButton" onClick={onClickEdit} value={id}>
        ✏
      </button>
      <button className="deleteButton" onClick={onClickDelete} value={id}>
        🗑
      </button>
    </div>
  );
};

export default CrudButtons;
