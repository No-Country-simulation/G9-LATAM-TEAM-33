import io
import json
import logging
import joblib
import sklearn
import oci

from fdk import response


def handler(ctx, data: io.BytesIO = None):
    try:
        signer = oci.auth.signers.get_resource_principals_signer()
        object_storage = oci.object_storage.ObjectStorageClient(
            config={}, signer=signer)
        namespace = object_storage.get_namespace().data
    except Exception as ex:
        logging.getLogger().info(f"error: {str(ex)}")
        raise

    name = "World"
    try:
        # get object from bucket
        bucket_response = object_storage.get_object(
            namespace_name=namespace, bucket_name="hackathon-model-bucket", object_name="live/modelo_consumo.pkl")
        # The response contains a stream
        model_bytes = bucket_response.data.content
        # print(f"model bytes: {model_bytes}")
        model = joblib.load(io.BytesIO(model_bytes))
        print(f"modelo cargado: {type(model)}")
        print(f"modelo cargado: {model}")

        body = json.loads(data.getvalue())
        name = body.get("name")
    except (Exception, ValueError) as ex:
        logging.getLogger().info('error parsing json payload: ' + str(ex))

    logging.getLogger().info("Inside Python Hello World function")
    return response.Response(
        ctx, response_data=json.dumps(
            {"message": f"Hello {name}. model: {model}"}),
        headers={"Content-Type": "application/json"}
    )
