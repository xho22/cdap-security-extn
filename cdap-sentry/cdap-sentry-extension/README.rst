================================================
CDAP Authorization Extension using Apache Sentry
================================================

Overview
========

This project integrates CDAP with Apache Sentry to delegate authorization (both ACL Management and Enforcement) to
Apache Sentry. It implements the CDAP
`Authorizer <https://github.com/caskdata/cdap/blob/develop/cdap-security/src/main/java/co/cask/cdap/security/authorization/Authorizer.java>`_
interface to achieve this integration. This project is implemented as a CDAP Authorizer extension.

Code Organization
=================

This code is organized into three submodules:

CDAP Sentry Model
-----------------

Defines the CDAP Data Model in Sentry. Used in both the Sentry Service and the Sentry Client (which runs inside the
CDAP Master).

CDAP Sentry Policy Engine
-------------------------

Defines authorization policies for CDAP entities in Sentry. It depends on the CDAP Sentry Model. It runs inside the
Sentry Service and is not required in the Sentry Client.

CDAP Sentry Binding
-------------------

Defines binding between the CDAP entities and the CDAP Data Model in the Sentry Service. Runs inside the Sentry Client
which runs inside the CDAP Master.

Building
========

Prerequisites
-------------
Since Apache Sentry releases are source-only, this project assumes that Sentry has been pre-built and installed in the
local maven repo. To do this, in the Apache Sentry source root directory please run::

  mvn clean install -DskipTests


To build the CDAP Sentry Extension and run tests, execute the following command from the ``cdap-sentry``
root directory::

  mvn clean package


To skip tests, execute::

   mvn clean package -DskipTests


Deploying
=========

The server side code only requires CDAP Sentry Policy and the CDAP Sentry Model classes. So, the
``cdap-sentry-policy/target/cdap-sentry-policy-*.jar`` and ``cdap-sentry-model/target/cdap-sentry-model-*.jar``
should be deployed in the ``[SENTRY_HOME_DIR]/lib`` directory on the Sentry Service host. After updating these
jars, please restart the Sentry Service.

The client side requires the CDAP Sentry Binding as well as its dependencies. To deploy the cdap-sentry
authorization extension:

- Install the ``cdap-sentry-binding/target/cdap-sentry-binding-*.jar`` at a known location on your CDAP Master host.
- Set the following properties in in the ``cdap-site.xml`` that the Master uses:

.. list-table::
   :widths: 20 80
   :header-rows: 1

   * - Parameter
     - Value
   * - ``security.authorization.authorizer.extension.jar.path``
     - Absolute path of the ``cdap-sentry-binding-*.jar`` on the local file system of the CDAP Master.
   * - ``security.authorization.authorizer.extension.config.sentry.site.url``
     - Absolute path of the client-side ``sentry-site.xml`` on the local file system of the CDAP Master.
   * - ``security.authorization.authorizer.extension.config.sentry.admin.group``
     - A unix group configured as an admin group in the Sentry Service (identified by ``sentry.service.admin.group``
       in the ``sentry-site.xml`` used by the Sentry Service). This group is used while granting ``ALL`` privileges
       to a user when he/she successfully creates an entity.
   * - ``security.authorization.authorizer.extension.config.superusers``
     - Comma-separated list of super users. Super users are authorized to perform all operations on all entities.
       They can also manage roles.
   * - ``security.authorization.authorizer.extension.config.instance.name``
     - String to use to identify the CDAP Instance. Defaults to 'cdap'.

- Restart CDAP Master.

Share and Discuss!
==================

Have a question? Discuss at the `CDAP User Mailing List <https://groups.google.com/forum/#!forum/cdap-user>`__.

License
=======

Copyright © 2016 Cask Data, Inc.

Licensed under the Apache License, Version 2.0 (the "License"); you may
not use this file except in compliance with the License. You may obtain
a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0