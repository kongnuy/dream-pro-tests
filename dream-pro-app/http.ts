import axios, {AxiosInstance, AxiosResponse} from 'axios';

export const http = {
  get,
  post,
  put,
  remove,
  patch,
  getInstance,
};

async function getInstance(multipart?: string): Promise<AxiosInstance> {
  return new Promise<AxiosInstance>(async (resolve, reject) => {
    const headers = await getHeaders(multipart);
    resolve(
      axios.create({
        timeout: 60000,
        headers: headers,
        baseURL: 'http://44.202.152.37/v1',
      }),
    );
  });
}

async function getHeaders(multipart?: string) {
  const headers = {
    Accept: 'application/json',
    'Content-Type': multipart || 'application/json',
  };

  return headers;
}

function getBody(params: any) {
  return params;
}
async function get(path: string): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .get(path)
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function post(path: string, data?: any, multipart?: any): Promise<any> {
  const axiosInstance = await getInstance(multipart);
  return axiosInstance
    .post(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function put(path: string, data?: any): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .put(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function patch(path: string, data?: any): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .patch(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function remove(path: string, data?: any): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .delete(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

function handleResponse(res: AxiosResponse) {
  console.log(res.data);

  return res.data;
}

function handleError(err: any) {
  let data = null;
  let status = 500;
  let message =
    "Une érreur est survenu lors de l'opération. Merci de reéssayer !";

  console.log('<== Request Error ==> ');

  if (err && err.response) {
    console.log(err.response.data);
    console.log(err.response.status);
    console.log(err.response.config);
    data = err.response.data;
    status = err.response.status;
    if (err && err.response && err.response.data && err.response.data.message) {
      message = err.response.data.message;
    }
  } else if (err && err.request) {
    console.log(err.request);
  } else {
    console.log(err.message);
  }
  console.log('<== End request Error ==> ');
  console.log('==> status ' + status);

  return {success: false, status, data, message};
}
