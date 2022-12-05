import { api } from "../../api.js";
import axios from "axios";

const config = {
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
  },
};

const analyzeText = async (
  data,
  successCallback = () => {},
  failureCallback = () => {}
) => {
  try {
    const text = await axios.post(api.text.analyzer, data, config);
    successCallback(text);
  } catch (err) {
    failureCallback(err);
  }
};

export default analyzeText;
