import React from "react";
import { Router } from "react-router-dom";
import { Link } from "react-router-dom";

export default function CreateProjectButton() {
  return (
    <React.Fragment>
      <Link to="/addProject" className="btn btn-lg btn-info">
        Create a Project
      </Link>
    </React.Fragment>
  );
}
