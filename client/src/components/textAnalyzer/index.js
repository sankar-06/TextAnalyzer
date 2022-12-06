import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Table from "react-bootstrap/Table";
import analyzeText from "./api";
import { analysis as analysisArray } from "../../constants/analysis";
import { fields } from "../../constants/fields";

const TextAnalyzer = () => {
  const [text, setText] = useState("");
  const [analysis, setAnalysis] = useState(analysisArray);

  const handleSubmit = (e) => {
    analyzeText(
      { content: text },
      ({ data }) => {
        setAnalysis(data);
      },
      (err) => {
        setAnalysis(err.response.data.content);
        console.log(err);
      }
    );
    e.preventDefault();
  };

  return (
    <div className="d-flex flex-column">
      <div className="input">
        <Form>
          <Form.Group className="mb-3" controlId="formBasicInput">
            <Form.Label>Text</Form.Label>
            <Form.Control
              as="textarea"
              rows={5}
              cols={50}
              value={text}
              placeholder="Enter text to analyze"
              onChange={(e) => {
                setText(e.target.value);
              }}
            />
          </Form.Group>

          <Button
            variant="primary"
            style={{ float: "right" }}
            onClick={() => handleSubmit()}
          >
            Submit
          </Button>
        </Form>
      </div>
      <div className="response">
        <Form.Label>Analysis</Form.Label>
        <Table striped bordered hover className="mt-3">
          <tbody>
            {Object.keys(analysis).map((key, index) => {
              return (
                <tr key={index}>
                  <td>{fields[key]}</td>
                  <td>{analysis[key]}</td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    </div>
  );
};

export default TextAnalyzer;
